package Optimize;

import Assembly.AsmBlock;
import Assembly.AsmFunction;
import Assembly.AsmModule;
import Assembly.Instruction.*;
import Assembly.Operand.*;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

import static Assembly.Operand.PhyReg.*;

public class ASMRegColor {
    AsmFunction curFunction;
    int K = 26;

    //活跃性计算 liveness analysis
    HashMap<AsmBlock, HashSet<Reg>> setDEF = new HashMap<>();  //HashSet集合
    HashMap<AsmBlock, HashSet<Reg>> setUse = new HashMap<>();
    HashMap<AsmBlock, HashSet<Reg>> setIn = new HashMap<>();
    HashMap<AsmBlock, HashSet<Reg>> setOut = new HashMap<>();
    HashSet<Reg> initial = new HashSet<>();   //临时寄存器集合 元素没有预着色或者被处理

    //静态活跃分析构造冲突图 build
    HashSet<Pair<Reg, Reg>> adjSet = new HashSet<>();  //图中冲突边（u，v）【无向边】的集合
    HashMap<Reg, HashSet<Reg>> adjList = new HashMap<>();   //图的邻接表表示
    HashSet<Reg> precolored = new HashSet<>();  //机器寄存器集合 每个寄存器被预先指派了一种颜色
    HashMap<Reg, Integer> degree = new HashMap<>();   //包含每个节点当前度数的数组
    HashMap<Reg, HashSet<AsmMv>> moveList = new HashMap<>();  //从一个节点到其相关传送指令集的映射

    //传送指令集合
    HashSet<AsmMv> coalescesMoves = new HashSet<>(); //已经合并的传送指令集合
    HashSet<AsmMv> constrainedMoves = new HashSet<>();  //源操作数和目标操作数冲突的传送指令集合
    HashSet<AsmMv> frozenMoves = new HashSet<>();   //不再考虑合并的传送指令集合
    HashSet<AsmMv> workListMoves = new HashSet<>();  //可能合并的传送指令集合
    HashSet<AsmMv> activeMoves = new HashSet<>();   //还未做好合并准备的传送指令集合

    //初始化:简化、合并、冻结、溢出
    HashSet<Reg> simplifyWorkList = new HashSet<>();  //低度数的传送无关的结点表
    HashSet<Reg> freezeWorkList = new HashSet<>();   //低度数的传送有关的结点表
    HashSet<Reg> spillWorkList = new HashSet<>();   //高度数的结点表

    HashSet<Reg> spilledNodes = new HashSet<>();  //本轮要被溢出的结点集合
    HashSet<Reg> coalescedNodes = new HashSet<>();  //已合并的寄存器集合
    HashSet<Reg> coloredNodes = new HashSet<>();  //成功着色的结点集合
    Stack<Reg> selectStack = new Stack<>(); //一个包含从图中删除的临时变量的栈

    HashMap<Reg, Reg> alias = new HashMap<>(); //u,v合并后的集合

    //着色 assign color
    HashMap<Reg, Integer> color = new HashMap<>();
    HashSet<Reg> newTmp = new HashSet<>();
    //for optimize:
//    public ArrayList<PhyReg> caller = new ArrayList<>();
//    public ArrayList<PhyReg> callee = new ArrayList<>();
//    public ArrayList<Integer> OKColor = new ArrayList<>();
    public boolean isInitialized=false;

    public void visit(AsmModule it) {
        for (var func : it.functions) {
            isInitialized=false;
            curFunction=func;
            visit(func);
        }
    }

//    void addRegCall(int l, int r, ArrayList<PhyReg> regs, ArrayList<PhyReg> call) {
//        for (int i = l; i <= r; ++i) {
//            call.add(regs.get(i));
//        }
//    }
//    HashSet<PhyReg> allRegs = new HashSet<>();
//    public void regInitial() {
//        allRegs.addAll(PhyReg.s);
//        allRegs.addAll(PhyReg.t);
//        allRegs.addAll(PhyReg.a);
//        allRegs.add(PhyReg.zero);
//        allRegs.add(PhyReg.ra);
//        allRegs.add(PhyReg.sp);
//        allRegs.add(PhyReg.gp);
//        allRegs.add(PhyReg.tp);
//
//        //regs：ArrayList<PhyReg> index对应i 对应32个寄存器
//        for(int i=0;i<=2;++i) regs.add(t(i));
//        for(int i=0;i<=1;++i) regs.add(s(i));
//        for(int i=0;i<=7;++i) regs.add(a(i));
//        for(int i=2;i<=11;++i) regs.add(s(i));
//        for(int i=3;i<=6;++i) regs.add(t(i));
//        caller.clear();
//        callee.clear();
//        caller.add(PhyReg.ra);
//        addRegCall(0, 2, PhyReg.t, caller);
//        addRegCall(0, 7, PhyReg.a, caller);
//        addRegCall(3, 6, PhyReg.t, caller);
//        callee.add(PhyReg.sp);
//        addRegCall(0, 11, PhyReg.s, callee);
//        for (int i = 5; i <= 7; ++i) OKColor.add(i);
//        for (int i = 9; i <= 31; ++i) OKColor.add(i);
//    }

    public void visit(AsmFunction it) {
        Initialize();
        while (true) {
            LivenessAnalysis();
            Build();
            MakeWorkList();
            while (true) {
                if (!simplifyWorkList.isEmpty()) Simplify();
                else if (!workListMoves.isEmpty()) Coalesce();
                else if (!freezeWorkList.isEmpty()) Freeze();
                else if (!spillWorkList.isEmpty()) SelectSpill();
                else break;
            }
            AssignColors();
            if (spilledNodes.isEmpty()) break;
            ReWrite();
        }
        //将color中的节点中的reg分配到对应的26个可以使用的物理寄存器上
        allocaByColor(it);
        it.savecall();
        //func的finish：计算栈指针
        it.finish();
    }

    public void allocaByColor(AsmFunction it){
        for (var block : it.blocks) {
            for (var inst = block.headInst; inst != null; inst = inst.next) {
                if (inst instanceof AsmBinaryS i) {
                    if (i.rs1 instanceof Reg r && color.containsKey(r)) i.rs1 = regs.get(color.get(r));
                    if (i.rs2 instanceof Reg r && color.containsKey(r)) i.rs2 = regs.get(color.get(r));
                    if (i.rd instanceof Reg r && color.containsKey(r)) i.rd = regs.get(color.get(r));
                } else if (inst instanceof AsmCmpS i) {
                    if (i.rs instanceof Reg r && color.containsKey(r)) i.rs = regs.get(color.get(r));
                    if (i.rd instanceof Reg r && color.containsKey(r)) i.rd = regs.get(color.get(r));
                } else if (inst instanceof AsmLa i) {
                    if ( color.containsKey(i.rd)) i.rd = regs.get(color.get(i.rd));
                } else if (inst instanceof AsmLi i) {
                    if ( color.containsKey(i.rd)) i.rd = regs.get(color.get(i.rd));
                } else if (inst instanceof AsmMemoryS i) {
                    if (color.containsKey(i.rs)) i.rs = regs.get(color.get(i.rs));
                    if (color.containsKey(i.rd)) i.rd = regs.get(color.get(i.rd));
                } else if (inst instanceof AsmMv i) {
                    if (color.containsKey(i.rs)) i.rs = regs.get(color.get(i.rs));
                    if (color.containsKey(i.rd)) i.rd = regs.get(color.get(i.rd));
                }else if(inst instanceof AsmBranch i){
                    if(i.cond instanceof Reg r && color.containsKey(r)) i.cond=regs.get(color.get(r));
                }
            }
        }
    }


    public void Initialize() {
        setDEF = new HashMap<>();
        setUse = new HashMap<>();
        setIn = new HashMap<>();
        setOut = new HashMap<>();
        initial = new HashSet<>();

        adjSet = new HashSet<>();
        precolored = new HashSet<>();
        degree = new HashMap<>();

        adjList = new HashMap<>();
        moveList = new HashMap<>();

        workListMoves = new HashSet<>();
        activeMoves = new HashSet<>();
        frozenMoves = new HashSet<>();
        constrainedMoves = new HashSet<>();
        coalescesMoves = new HashSet<>();

        simplifyWorkList = new HashSet<>();
        freezeWorkList = new HashSet<>();
        spillWorkList = new HashSet<>();

        coalescedNodes = new HashSet<>();
        coloredNodes = new HashSet<>();
        spilledNodes = new HashSet<>();

        selectStack = new Stack<>();
        alias = new HashMap<>();
        color = new HashMap<>();
        newTmp = new HashSet<>();

        setPrecolored();
        getUseDef();
        for (var uses : setUse.values()) initial.addAll(uses);
        for (var defs : setDEF.values()) initial.addAll(defs);
        initial.removeAll(allRegs);
        for (var reg : initial) {
            degree.put(reg, 0);
            adjList.put(reg, new HashSet<>());
            moveList.put(reg, new HashSet<>());
            alias.put(reg, reg);
            color.put(reg, null);
        }
    }

    public void setPrecolored() {
        singlePrecolored(PhyReg.zero, 0);
        singlePrecolored(PhyReg.ra, 1);
        singlePrecolored(PhyReg.sp, 2);
        singlePrecolored(PhyReg.gp, 3);
        singlePrecolored(PhyReg.tp, 4);
        //debug:这里是s0或者fp 而不是前面已经加过的sp！！！！
        singlePrecolored(PhyReg.s(0), 8);
        for (int i = 0; i <= 2; ++i) singlePrecolored(PhyReg.t(i), 5 + i);
        singlePrecolored(PhyReg.s(1), 9);
        for (int i = 0; i <= 7; ++i) singlePrecolored(PhyReg.a(i), 10 + i);
        for (int i = 2; i <= 11; ++i) singlePrecolored(PhyReg.s(i), 16 + i);
        for (int i = 3; i <= 6; ++i) singlePrecolored(PhyReg.t(i), 25 + i);
    }

    public void singlePrecolored(PhyReg it, int i) {
        precolored.add(it);
        degree.put(it, 0x3fffffff);
        adjList.put(it, new HashSet<>());
        alias.put(it, it);
        moveList.put(it, new HashSet<>());
        color.put(it, i);
    }

    public void getUseDef() {
        for (var block : curFunction.blocks) {
            block.def = new HashSet<>();
            block.use = new HashSet<>();
            for (var inst = block.headInst; inst != null; inst = inst.next) {
                analysis(inst);
                //我们可以把 use[𝑝] ∪ (use[𝑛] − def [𝑝]) 视为 𝑝𝑛 的等效 use
                //把 def [𝑛] ∪ def [𝑝] 视为 𝑝𝑛 的等效 def
                for (var use : inst.use) {
                    if (!block.def.contains(use)) block.use.add(use);
                }
                block.def.addAll(inst.def);
            }
            setDEF.put(block, block.def);
            setUse.put(block, block.use);
            setIn.put(block, block.use);
            setOut.put(block, new HashSet<>());
        }
    }

    public void analysis(AsmInst inst) {
        if (inst instanceof AsmBinaryS i) {
            if (i.rs1 instanceof Reg r) inst.use.add(r);
            if (i.rs2 instanceof Reg r) inst.use.add(r);
            if (i.rd instanceof Reg r) inst.def.add(r);
        } else if (inst instanceof AsmCmpS i) {
            if (i.rs instanceof Reg r) inst.use.add(r);
            if (i.rd instanceof Reg r) inst.def.add(r);
        } else if (inst instanceof AsmLa i) {
             inst.def.add(i.rd);
        } else if (inst instanceof AsmLi i) {
            inst.def.add(i.rd);
        } else if (inst instanceof AsmMemoryS i) {
            //debug: i have forgotten the difference between sw and lw
            inst.use.add(i.rs);
            if(i.op.equals("sw")){
                inst.use.add(i.rd);
            }
            else inst.def.add(i.rd);
        } else if (inst instanceof AsmMv i) {
            inst.use.add(i.rs);
            inst.def.add(i.rd);
        } else if (inst instanceof AsmBranch i) {
            if (i.cond instanceof Reg r) inst.use.add(r);
        }
//        else if (inst instanceof AsmCall) {
//            inst.def.addAll(caller);
//            inst.use.addAll(callee);
//        }
    }


    //in[n]<-use[n]&(out[n]-def[n])
    //out[n]<-in[s],s属于succ[n]
    public void LivenessAnalysis() {
        while (true) {
            boolean flag = true;
            for (var block : curFunction.blocks) {
                HashSet<Reg> blockIn = setIn.get(block);
                HashSet<Reg> blockOut = setOut.get(block);
                int originInSize = blockIn.size();
                int originOutSize = blockOut.size();
                blockOut.removeAll(setDEF.get(block));
                blockIn.addAll(blockOut);
                for (var succ : block.succ) {
                    blockOut.addAll(setIn.get(succ));
                }
                if (originInSize != blockIn.size() || originOutSize != blockOut.size()) flag = false;
            }
            if (flag) break;
        }
    }

    //使用静态活跃分析的结果来构造冲突图，并初始化worklist_moves，使之包含程序中所有的传送指令
    public void Build() {
        for (var block : curFunction.blocks) {
            HashSet<Reg> live = new HashSet<>(setOut.get(block));
            for (var inst = block.tailInst; inst != null; inst = inst.prev) {
                if (inst instanceof AsmMv) {
                    live.removeAll(inst.use);
                    for (var def : inst.def) moveList.get(def).add((AsmMv) inst);
                    for (var use : inst.use) moveList.get(use).add((AsmMv) inst);
                    workListMoves.add((AsmMv) inst);
                }
                live.addAll(inst.def);
                for (var d : inst.def) {
                    for (var l : live) {
                        AddEdge(l, d);
                    }
                }
                live.removeAll(inst.def);
                live.addAll(inst.use);
            }
        }
    }

    public void AddEdge(Reg u, Reg v) {
        if (!adjSet.contains(new Pair<>(u, v)) && u != v) {
            adjSet.add(new Pair<>(u, v));
            adjSet.add(new Pair<>(v, u));
            if (!precolored.contains(u)) {
                adjList.get(u).add(v);
                degree.replace(u, degree.get(u) + 1);
            }
            if (!precolored.contains(v)) {
                adjList.get(v).add(u);
                degree.replace(v, degree.get(v) + 1);
            }
        }
    }

    public void MakeWorkList() {
        for (var n : initial) {
            if (degree.get(n) >= K) spillWorkList.add(n);
            else if (MoveRelated(n)) freezeWorkList.add(n);
            else simplifyWorkList.add(n);
        }
    }

    public HashSet<AsmMv> NodeMoves(Reg n) {
        HashSet<AsmMv> tmp = new HashSet<>(activeMoves);
        tmp.addAll(workListMoves);
        tmp.retainAll(moveList.get(n));
        return tmp;
    }

    public boolean MoveRelated(Reg n) {
        return !NodeMoves(n).isEmpty();
    }

    //初始化——简化:删除低度数节点
    public void Simplify() {
        Reg n = simplifyWorkList.iterator().next();
        simplifyWorkList.remove(n);
        selectStack.push(n);
        for (var m : Adjacent(n)) DecrementDegree(m);
    }

    //去掉n相邻点中：已经合并/溢出 的相邻点
    public HashSet<Reg> Adjacent(Reg n) {
        HashSet<Reg> tmp = new HashSet<>(adjList.get(n));
        selectStack.forEach(tmp::remove);
        tmp.removeAll(coalescedNodes);
        return tmp;
    }

    //将节点m的度数-1
    public void DecrementDegree(Reg m) {
        int d = degree.get(m);
        degree.replace(m, d - 1);
        if (d == K) {
            HashSet<Reg> newSet = Adjacent(m);
            newSet.add(m);
            EnableMoves(newSet);
            spillWorkList.remove(m);
            if (MoveRelated(m)) freezeWorkList.add(m);
            else simplifyWorkList.add(m);
        }
    }

    public void EnableMoves(HashSet<Reg> nodes) {
        for (var n : nodes) {
            for (var m : NodeMoves(n)) {
                if (activeMoves.contains(m)) {
                    activeMoves.remove(m);
                    workListMoves.add(m);
                }
            }
        }
    }

    //初始化：合并
    public void Coalesce() {
        AsmMv m = workListMoves.iterator().next();
        Reg x = getAlias(m.rd);
        Reg y = getAlias(m.rs);
        Reg u, v;
        if (precolored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        workListMoves.remove(m);
        if (u == v) {
            coalescesMoves.add(m);
            addWorkList(u);
        } else if (precolored.contains(v) || adjSet.contains(new Pair<>(u, v))) {
            constrainedMoves.add(m);
            addWorkList(u);
            addWorkList(v);
        } else {
            HashSet<Reg> newSet = new HashSet<>();
            newSet.addAll(Adjacent(u));
            newSet.addAll(Adjacent(v));
            if (precolored.contains(u) && judge(u, v) || !precolored.contains(u) && Conservative(newSet)) {
                coalescesMoves.add(m);
                Combine(u, v);
                addWorkList(u);
            } else activeMoves.add(m);
        }
    }

    //获取别名 并查集
    public Reg getAlias(Reg n) {
        if (coalescedNodes.contains(n)) return getAlias(alias.get(n));
        else return n;
    }

    public void addWorkList(Reg u) {
        if (!precolored.contains(u) && !MoveRelated(u) && degree.get(u) < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    public boolean judge(Reg u, Reg v) {
        for (var t : Adjacent(v)) {
            if (!OK(t, u)) return false;
        }
        return true;
    }

    public boolean OK(Reg t, Reg r) {
        return degree.get(t) < K || precolored.contains(t) || adjSet.contains(new Pair<>(t, r));
    }

    public void Combine(Reg u, Reg v) {
        if (freezeWorkList.contains(v)) freezeWorkList.remove(v);
        else spillWorkList.remove(v);

        coalescedNodes.add(v);
        alias.replace(v, u);
        HashSet<AsmMv> move = new HashSet<>(moveList.get(u));
        move.addAll(moveList.get(v));
        moveList.replace(u, move);
        HashSet<Reg> vv = new HashSet<>();
        vv.add(v);
        EnableMoves(vv);
        for (var t : Adjacent(v)) {
            AddEdge(t, u);
            DecrementDegree(t);
        }
        if (degree.get(u) >= K && freezeWorkList.contains(u)) {
            freezeWorkList.remove(u);
            spillWorkList.add(u);
        }
    }

    public boolean Conservative(HashSet<Reg> newSet) {
        int k = 0;
        for (var n : newSet) {
            if (degree.get(n) >= K) k++;
        }
        return k < K;
    }


    //初始化——冻结
    public void Freeze() {
        Reg u = freezeWorkList.iterator().next();
        freezeWorkList.remove(u);
        simplifyWorkList.add(u);
        FreezeMoves(u);
    }

    public void FreezeMoves(Reg u) {
        for (var m : NodeMoves(u)) {
            Reg v;
            if (getAlias(m.rs) == getAlias(u)) v = getAlias(m.rd);
            else v = getAlias(m.rs);
            activeMoves.remove(m);
            frozenMoves.add(m);
            if (NodeMoves(v).isEmpty() && degree.get(v) < K) {
                freezeWorkList.remove(v);
                simplifyWorkList.add(v);
            }
        }
    }

    public void SelectSpill() {
        Reg m = null;
        int maxDegree = -1;
        for (var reg : spillWorkList) {
            if (degree.get(reg) > maxDegree) {
                maxDegree = degree.get(reg);
                m = reg;
            }
        }
        spillWorkList.remove(m);
        simplifyWorkList.add(m);
        FreezeMoves(m);
    }

    //着色
    public void AssignColors() {
        while (!selectStack.isEmpty()) {
            Reg n = selectStack.pop();
            HashSet<Integer> okColors = new HashSet<>(OKColor);
            HashSet<Reg> colored = new HashSet<>(coloredNodes);
            colored.addAll(precolored);
            for (var w : adjList.get(n)) {
                if (colored.contains(getAlias(w))) okColors.remove(color.get(getAlias(w)));
            }
            if (okColors.isEmpty()) spilledNodes.add(n);
            else {
                coloredNodes.add(n);
                color.replace(n, okColors.iterator().next());
            }
        }
        for (var n : coalescedNodes) {
            color.replace(n, color.get(getAlias(n)));
        }
    }

    //重写
    AsmBlock curBlock = null;

    public void ReWrite() {
        for (var block : curFunction.blocks) {
            for (var inst = block.headInst; inst != null; inst = inst.next) {
                curBlock = block;
                visit(inst);
            }
            initial.clear();
            initial.addAll(coloredNodes);
            initial.addAll(coalescedNodes);
            initial.addAll(newTmp);
            spilledNodes.clear();
            coloredNodes.clear();
            coalescedNodes.clear();
        }
    }

    private Operand allocaFP(AsmInst ins, Operand operand, PhyReg reg, boolean isLoad) {
        if (operand instanceof VirReg v) {
            if (!curFunction.containsReg(v)) curFunction.allocate(v);
            int offset = curFunction.getVarRegOffset(v);
            newTmp.add(reg);
            if (-2048 < offset && offset < 2048) {
                if (isLoad) curBlock.insert_before(ins, new AsmMemoryS("lw", reg, fp, offset));
                else curBlock.insert_after(ins, new AsmMemoryS("sw", reg, fp, offset));
            } else {
                PhyReg tfp = t(4);
                if (isLoad) {
                    curBlock.insert_before(ins, new AsmLi(tfp, new Imm(offset)));
                    curBlock.insert_before(ins, new AsmBinaryS("add", tfp, fp, tfp));
                    curBlock.insert_before(ins, new AsmMemoryS("lw", reg, tfp, 0));
                } else {
                    curBlock.insert_after(ins, new AsmMemoryS("sw", reg, tfp, 0));
                    curBlock.insert_after(ins, new AsmBinaryS("add", tfp, fp, tfp));
                    curBlock.insert_after(ins, new AsmLi(tfp, new Imm(offset)));
                }
            }
            return reg;
        } else return operand;
    }


    public void visit(AsmInst it) {
        if (it instanceof AsmBinaryS ins) visit(ins);
        else if (it instanceof AsmLi ins) visit(ins);
        else if (it instanceof AsmLa ins) visit(ins);
        else if (it instanceof AsmMemoryS ins) visit(ins);
        else if (it instanceof AsmMv ins) visit(ins);
        else if (it instanceof AsmCmpS ins) visit(ins);
        else if (it instanceof AsmBranch ins) visit(ins);
    }

    public void visit(AsmBinaryS it) {
        //先load后store    a=a+1
        if (spilledNodes.contains((Reg) it.rs1)) it.rs1 = allocaFP(it, it.rs1, t(0), true);
        if (it.rs2 instanceof Reg && spilledNodes.contains((Reg) it.rs2)) it.rs2 = allocaFP(it, it.rs2, t(1), true);
        if (spilledNodes.contains((Reg) it.rd)) it.rd = allocaFP(it, it.rd, t(2), false);
    }

    public void visit(AsmLi it) {
        if (spilledNodes.contains(it.rd)) it.rd = (Reg) allocaFP(it, it.rd, t(0), false);
    }

    public void visit(AsmLa it) {
        if (spilledNodes.contains(it.rd)) it.rd = (Reg) allocaFP(it, it.rd, t(0), false);
    }

    public void visit(AsmBranch it) {
        if (spilledNodes.contains((Reg) it.cond)) it.cond = allocaFP(it, it.cond, t(0), true);
    }

    public void visit(AsmMemoryS it) {
        if (it.op.equals("sw")) {
            if (spilledNodes.contains(it.rs)) it.rs = (Reg) allocaFP(it, it.rs, t(0), true);
            if (spilledNodes.contains(it.rd)) it.rd = (Reg) allocaFP(it, it.rd, t(1), true);
        } else {
            if (spilledNodes.contains(it.rs)) it.rs = (Reg) allocaFP(it, it.rs, t(0), true);
            if (spilledNodes.contains( it.rd)) it.rd = (Reg) allocaFP(it, it.rd, t(1), false);
        }
    }

    public void visit(AsmMv it) {
        if (spilledNodes.contains( it.rd)) it.rd = (Reg) allocaFP(it, it.rd, t(0), false);
        if (spilledNodes.contains( it.rs)) it.rs = (Reg) allocaFP(it, it.rs, t(1), true);
    }

    public void visit(AsmCmpS it) {
        if (spilledNodes.contains((Reg) it.rd)) it.rd = allocaFP(it, it.rd, t(0), false);
        if (spilledNodes.contains((Reg) it.rs)) it.rs = allocaFP(it, it.rs, t(1), true);
    }


}
