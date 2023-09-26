package BackEnd;

import Assembly.AsmBlock;
import Assembly.AsmData;
import Assembly.AsmFunction;
import Assembly.AsmModule;
import Assembly.Instruction.*;
import Assembly.Operand.Imm;
import Assembly.Operand.PhyReg;
import Assembly.Operand.Reg;
import Assembly.Operand.VirReg;
import IR.Entity.IRConst;
import IR.Entity.IREntity;
import IR.Entity.IRGlobalVal;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRProgram;
import IR.IRVisitor;
import IR.Inst.*;
import IR.Type.IRClassType;
import Optimize.ASMRegColor;
import Util.BuiltinElements;
import Util.Error.codgenError;
import Util.Error.error;

import java.util.ArrayList;
import java.util.HashMap;

import static Assembly.Operand.PhyReg.*;
import static Assembly.Operand.PhyReg.caller;

public class InsSelector implements IRVisitor {
    public AsmModule module;
    public AsmFunction curFunction;
    public AsmBlock curBlock;
    public HashMap<IREntity, Reg> regMap = new HashMap<>();
    public HashMap<IRBasicBlock, AsmBlock> blockMap = new HashMap<>();

    //for callee:
    public ArrayList<Reg> calleeVirReg=new ArrayList<>();

    public InsSelector(AsmModule module) {
        this.module = module;
    }

    public VirReg newVirReg(IREntity entity) {
        VirReg rg = new VirReg();
        regMap.put(entity, rg);
        return rg;
    }

    private void addInst(AsmInst inst) {
        curBlock.push_back(inst);
    }

    private Reg getReg(IREntity entity) {
        if (entity instanceof IRConst && ((IRConst) entity).cType != IRConst.constType.STRING) {
            if (((IRConst) entity).cType == IRConst.constType.INT) {
                int val = ((IRConst) entity).i32;
//                if (val == 0) return zero;
                Reg reg = new VirReg();
                addInst(new AsmLi(reg, new Imm(val)));
                return reg;
            } else if (((IRConst) entity).cType == IRConst.constType.BOOL) {
                boolean val = ((IRConst) entity).i1;
                if (!val) return zero;
                Reg reg = new VirReg();
                addInst(new AsmLi(reg, new Imm(1)));
                return reg;
            } else if (((IRConst) entity).cType == IRConst.constType.VOID || ((IRConst) entity).cType == IRConst.constType.NULL) {
                return zero;
            }
        } else if (entity instanceof IRGlobalVal g) {
            Reg reg = new VirReg();              // la: 将一个标号（label）或全局变量的地址加载到寄存器中
            addInst(new AsmLa(reg, g.name));
            return reg;
        }
        Reg reg = regMap.get(entity);
        if (reg == null) reg = newVirReg(entity);
        return reg;
    }

    private int getConstVal(IREntity entity) {
        if (((IRConst) entity).cType == IRConst.constType.INT) return ((IRConst) entity).i32;
        else if (((IRConst) entity).cType == IRConst.constType.BOOL) return ((IRConst) entity).i1 ? 1 : 0;
        else if (((IRConst) entity).cType == IRConst.constType.NULL) return 0;
        else return -19260817;
    }

    private void collectBlock(IRBasicBlock block) {
        String label = block.name.equals("entry") ? curFunction.name : block.name + "_" + curFunction.id;
        var asmBlock = new AsmBlock(label);
        if (block.isReturned) asmBlock.isReturned = true;
        blockMap.put(block, asmBlock);
        curFunction.addBlock(asmBlock);
    }

    @Override
    public void visit(IRProgram it) {
        PhyReg.regInitial();
        it.globalVarList.forEach(vara -> vara.accept(this));
        it.funcList.forEach(func -> func.accept(this));
    }

    @Override
    public void visit(IRFunction it) {
        curFunction = new AsmFunction(it.name);
        module.addFunction(curFunction);

        //先收集所有的block并存在blockMap和function中
        blockMap.clear();
        for (IRBasicBlock block : it.blocks) {
            collectBlock(block);
        }

        curBlock = blockMap.get(it.entry);

//        for callee
//        calleeVirReg=new ArrayList<>();
//        for(var reg:callee){
//            Reg newReg=new VirReg();
//            AsmMv mv=new AsmMv(newReg,reg);
//            calleeVirReg.add(newReg);
//            addInst(mv);
//        }

        for (int i = 0; i < 8; ++i) {
            if (i == it.params.size()) break;
            IREntity para = it.params.get(i);
            VirReg rd = newVirReg(para);
            addInst(new AsmMv(rd, a(i)));
        }

        for (int i = 8; i < it.params.size(); ++i) {
            IREntity para = it.params.get(i);
            VirReg rd = newVirReg(para);
            addInst(new AsmMemoryS("lw", rd, fp, (i - 8) << 2));
        }

        it.blocks.forEach(block -> block.accept(this));
    }

    @Override
    public void visit(IRBasicBlock it) {
        curBlock = blockMap.get(it);
        it.insts.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(IRClassType it) {

    }

    @Override
    public void visit(IRGlobalVal it) {
        //String a="ii"
        if (it.isString) {
            String str = ((IRConst) it.init).getStr();
            module.addData(new AsmData(it.name, str));
        }
        //int a=b;
        else if (it.init instanceof IRGlobalVal g) {
            module.addData(new AsmData(it.name, g.name, false));
        }
        //int c=1; int m
        else {
            int val = getConstVal(it.init);
            module.addData(new AsmData(it.name, val));
        }
    }


    @Override
    public void visit(allocateInst it) {
        VirReg rd = newVirReg(it.res);
        curFunction.allocate(rd);
    }

    @Override
    public void visit(binaryInst it) {
        VirReg rd = newVirReg(it.res);
        var inst = switch (it.op) {
            case "add" -> new AsmBinaryS("add", rd, getReg(it.lhs), getReg(it.rhs));
            case "sub" -> new AsmBinaryS("sub", rd, getReg(it.lhs), getReg(it.rhs));
            case "mul" -> new AsmBinaryS("mul", rd, getReg(it.lhs), getReg(it.rhs));
            case "sdiv" -> new AsmBinaryS("div", rd, getReg(it.lhs), getReg(it.rhs));
            case "srem" -> new AsmBinaryS("rem", rd, getReg(it.lhs), getReg(it.rhs));
            case "shl" -> new AsmBinaryS("sll", rd, getReg(it.lhs), getReg(it.rhs));
            case "ashr" -> new AsmBinaryS("sra", rd, getReg(it.lhs), getReg(it.rhs));
            case "and" -> new AsmBinaryS("and", rd, getReg(it.lhs), getReg(it.rhs));
            case "or" -> new AsmBinaryS("or", rd, getReg(it.lhs), getReg(it.rhs));
            case "xor" -> new AsmBinaryS("xor", rd, getReg(it.lhs), getReg(it.rhs));
            default -> throw new codgenError("it should not exist", null);
        };
        addInst(inst);
    }


    @Override
    public void visit(callInst it) {
        //参数一般都放在a【0-7】
        //进行函数调用时，每个参数都使用一个寄存器。因此根据 RISC-V Calling Convention，
        //函数的第一至第八个入参分别存放于 a0-7 中，剩下的参数将存放于栈中（sp 指向第一个放不下的参数），返回值放在 a0
//        int paraOffset = 0;
//        if (it.args != null) {
//            for (int i = 0; i < Integer.min(8, it.args.size()); ++i) {
//                addInst(new AsmMv(a(i), getReg(it.args.get(i))));
//            }
//            for (int i = 8; i < it.args.size(); ++i) {
//                addInst(new AsmMemoryS("sw", getReg(it.args.get(i)), sp, paraOffset));
//                paraOffset += 4;
//            }
//        }
//        curFunction.paraOffset = Integer.max(paraOffset, curFunction.paraOffset);
//        addInst(new AsmCall(it.funcName));
//        if (it.res == null || it.returnType.equals(BuiltinElements.irVoidType)) return;
//        addInst(new AsmMv(newVirReg(it.res), gp));
        ArrayList<Reg> paras=new ArrayList<>();
        if (it.args != null) {
            for (int i = 0; i < Integer.min(8, it.args.size()); ++i) {
                 paras.add(getReg(it.args.get(i)));
            }
            for (int i = 8; i < it.args.size(); ++i) {
                paras.add(getReg(it.args.get(i)));
            }
        }

        AsmCall CallInst=new AsmCall(it.funcName);
        addInst(CallInst);
        for (var call : caller) {
            curBlock.insert_before(CallInst, new AsmMemoryS("sw", call, fp, -(curFunction.offset += 4)));
            curBlock.insert_after(CallInst, new AsmMemoryS("lw", call, fp, -curFunction.offset));
        }
        int paraOffset = 0;
        if (it.args != null) {
            for (int i = 0; i < Integer.min(8, it.args.size()); ++i) {
                curBlock.insert_before(CallInst,new AsmMv(a(i), paras.get(i)));
            }
            for (int i = 8; i < it.args.size(); ++i) {
                curBlock.insert_before(CallInst,new AsmMemoryS("sw", paras.get(i) , sp, paraOffset));
                paraOffset += 4;
            }
        }
        curFunction.paraOffset = Integer.max(paraOffset, curFunction.paraOffset);
        if (it.res == null || it.returnType.equals(BuiltinElements.irVoidType)) return;
        curBlock.insert_after(CallInst,new AsmMv(newVirReg(it.res), a(0)));
    }
    @Override
    public void visit(getelementptrInstr it) {
        Reg rd = newVirReg(it.res);
        IREntity index = it.indexList.get(it.indexList.size() - 1);
        Reg id = getReg(index);
        if (id == zero) addInst(new AsmMv(rd, getReg(it.ptr)));
        else {
            Reg tmp = new VirReg();
            addInst(new AsmBinaryS("slli", tmp, id, new Imm(2)));
            addInst(new AsmBinaryS("add", rd, getReg(it.ptr), tmp));
        }
    }

    @Override
    public void visit(lcmpInst it) {
        //指令将根据比较结果设置目标寄存器的值。如果条件成立，则目标寄存器相应位被置为1，否则被置为0
        VirReg rd = newVirReg(it.res);
        Reg rs1 = getReg(it.lhs);
        Reg rs2 = getReg(it.rhs);
        //可以将比较结果存在寄存器中
        switch (it.cond) {
            case "slt" -> addInst(new AsmBinaryS("slt", rd, rs1, rs2)); //<
            case "sgt" -> addInst(new AsmBinaryS("slt", rd, rs2, rs1)); //>
            case "sle" -> {   // <=
                addInst(new AsmBinaryS("slt", rd, rs2, rs1));
                addInst(new AsmBinaryS("xori", rd, rd, new Imm(1)));
            }
            case "sge" -> {    //>=
                addInst(new AsmBinaryS("slt", rd, rs1, rs2));
                addInst(new AsmBinaryS("xori", rd, rd, new Imm(1)));
            }
            case "eq" -> {
                var tmp = new VirReg();
                addInst(new AsmBinaryS("xor", tmp, rs1, rs2));
                //if(tmp==0) rd=1;else rd=0;
                addInst(new AsmCmpS("seqz", rd, tmp));
            }
            case "ne" -> {
                var tmp = new VirReg();
                addInst(new AsmBinaryS("xor", tmp, rs1, rs2));
                //if(tmp==0) rd=0;else rd=1;
                addInst(new AsmCmpS("snez", rd, tmp));
            }
        }
    }

    @Override
    public void visit(loadInst it) {
        VirReg rd = newVirReg(it.res);
        Reg rs = getReg(it.pointer);
        if (it.pointer instanceof IRGlobalVal) {
            addInst(new AsmMemoryS("lw", rd, rs, 0));
        } else if (curFunction.containsReg(rs)) addInst(new AsmMv(rd, rs));
        else addInst(new AsmMemoryS("lw", rd, rs, 0));
    }

    @Override
    public void visit(storeInst it) {
        Reg rs = getReg(it.value);
        Reg rd = getReg(it.pointer);
        if (it.pointer instanceof IRGlobalVal) {
            addInst(new AsmMemoryS("sw", rs, rd, 0));
        } else if (curFunction.containsReg(rd)) addInst(new AsmMv(rd, rs));
        else addInst(new AsmMemoryS("sw", rs, rd, 0));
    }

    @Override
    public void visit(retInst it) {
        //当函数执行完成并准备返回时，将结果存储到 a0 寄存器中，然后使用 ret 指令跳转回调用者。调用者可以从 a0 寄存器中读取返回值。
        if (it.value != BuiltinElements.irVoidConst) addInst(new AsmMv(a(0), getReg(it.value)));
//        for(int i=0;i<calleeVirReg.size();++i){
//            Reg reg=callee.get(i);
//            addInst(new AsmMv(reg,calleeVirReg.get(i)));
//        }
        addInst(new AsmRet());
    }

    @Override
    public void visit(jumpInst it) {
        //for livenessAnalysis
        curBlock.add_succ(blockMap.get(it.toBlock));
        blockMap.get(it.toBlock).add_pred(curBlock);
        //
        addInst(new AsmJ(blockMap.get(it.toBlock).label));
    }

    @Override
    public void visit(brInst it) {
        //for livenessAnalysis
        curBlock.add_succ(blockMap.get(it.elseBlock));
        blockMap.get(it.elseBlock).add_pred(curBlock);
        //beqz : branch if equals to zero
        addInst(new AsmBranch("beqz", getReg(it.cond), blockMap.get(it.elseBlock).label));

        curBlock.add_succ(blockMap.get(it.thenBlock));
        blockMap.get(it.thenBlock).add_pred(curBlock);
        addInst(new AsmJ(blockMap.get(it.thenBlock).label));
    }

    //we do not need these instructions
    @Override
    public void visit(phiInst it) {

    }

    @Override
    public void visit(selectInst it) {

    }

    @Override
    public void visit(declareInst it) {

    }
}
