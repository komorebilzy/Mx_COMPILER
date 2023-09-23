package IR;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.Type.IRType;
import IR.Type.IRVoidType;
import MiddleEnd.IRBuilder;
import Util.BuiltinElements;
import IR.Inst.*;

import javax.swing.text.html.parser.Entity;
import java.rmi.registry.Registry;
import java.security.PublicKey;
import java.util.*;

public class IRFunction {
    public String name;
    public IRType returnType;
    public IRBasicBlock entry = new IRBasicBlock("entry",this);
    public ArrayList<IREntity> params = new ArrayList<>();
    public ArrayList<IRBasicBlock> blocks = new ArrayList<>();
    public LinkedList<allocateInst> allocas = new LinkedList<>();
    public long labelNum = 0;
    public long var = -1;
    public boolean isReturned = false;

    public IRFunction(String name, IRType returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public IRFunction(String name, IRType returnType, IREntity... para) {
        this.name = name;
        this.returnType = returnType;
        params.addAll(List.of(para));
    }

    public void addAlloca() {
        for (int i = 0; i < allocas.size(); ++i) {
            entry.insts.addFirst(allocas.get(i));
        }
    }

    public String toString() {
        for (int i = 0; i < allocas.size(); ++i) {
            entry.insts.addFirst(allocas.get(i));
        }
        StringBuilder ans = new StringBuilder("define " + returnType.toString() + " @" + name + "(");
        for (int i = 0; i < params.size(); ++i) {
            ans.append(params.get(i).toString());
            if (i != params.size() - 1) ans.append(", ");
        }
        ans.append(") {\n");
        for (IRBasicBlock block : blocks) {
            ans.append(block.toString()).append("\n");
        }
        ans.append("}\n");
        return ans.toString();
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    public static IRFunction globalVarInit(String name) {
        IRFunction func = new IRFunction("_mx_global_var_init_of_" + name, BuiltinElements.irVoidType);
        return func;
    }

    public String getLabel() {
        return "block_" + Long.toString(++labelNum);
    }

    public String getRegId() {
        return "." + Long.toString(++var);
    }


    //for optimize :
    public HashMap<String,ArrayList<storeInst>> storeIns=new HashMap<>();
    public HashMap<String,ArrayList<loadInst>> loadIns=new HashMap<>();

    //

    public ArrayList<ArrayList<BitSet>> dom = null;
    public ArrayList<IRBasicBlock> RPO = null;  //Reverse Post Order

    public ArrayList<IRBasicBlock> getRPO() {
        //construct the graph
        int num=-1;
        for (var block : blocks) {
            var inst = block.insts.getLast();
            block.number=++num;
            if (inst instanceof brInst) {
                block.nextBlocks.add(((brInst) inst).thenBlock);
                block.nextBlocks.add(((brInst) inst).elseBlock);
            } else if (inst instanceof jumpInst) {
                block.nextBlocks.add(((jumpInst) inst).toBlock);
            }
        }
        //getRPO(逆后序)
        Queue<IRBasicBlock> queue = new ArrayDeque<>();
        queue.add(blocks.get(0));
        RPO.add(blocks.get(0));
        while (!queue.isEmpty()) {
            IRBasicBlock node = queue.poll();
            if (node.nextBlocks != null && node.nextBlocks.size() != 0) {
                for (var block : node.nextBlocks) {
                    queue.add(block);
                    RPO.add(block);
                    block.preBlocks.add(node);
                }
            }
        }
        return RPO;
    }


    //确定支配集
    public void getDom() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (var u : RPO) {
                BitSet tmp = new BitSet(500);
                tmp.set(u.number);
                for (var v : u.preBlocks) {
                    tmp.and(v.dom);
                }
                if (tmp != u.dom) {
                    u.dom = tmp;
                    flag = true;
                }
            }
        }
        for (var u : RPO) {
            for (int j = 1; j < 500; ++j) {
                if (u.dom.get(j)) u.Dom.add(blocks.get(j));
            }
        }
    }

    //确定直接支配节点:
    //只需要找到支配集元素个数为当前节点的支配集元素个数减 1 的节点
    public void getIDom(){

    }


    //构建支配树


}

