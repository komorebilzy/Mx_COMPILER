package IR;

import IR.Inst.*;
import org.antlr.v4.codegen.model.ArgAction;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;

public class IRBasicBlock {
    public String name;
    public LinkedList<IRInst> insts = new LinkedList<>();
    public IRFunction inFunc;
    public boolean isFinished = false;
    public boolean isReturned=false;

    public IRBasicBlock(IRFunction inFunc) {
        this.name = inFunc.getLabel();
        this.inFunc = inFunc;
    }

    public IRBasicBlock(String name) {
        this.name = name;
    }

    public IRBasicBlock(String name,IRFunction inFunc) {
        this.name = name;
        this.inFunc = inFunc;
    }

    public String getLabel() {
        return "%" + name;
    }

    public void addInst(IRInst inst) {
        if (!isFinished) this.insts.add(inst);
        if (inst instanceof jumpInst || inst instanceof brInst || inst instanceof retInst) isFinished = true;
    }

    public String toString() {
        String ans = name + ":\n";
        for (IRInst inst : insts) {
            ans += " " + inst + "\n";
        }
        return ans;
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }


    //for optimize:
    public ArrayList<IRBasicBlock> nextBlocks=new ArrayList<>();

    public ArrayList<IRBasicBlock> preBlocks=new ArrayList<>();

    public BitSet dom=new BitSet(500);
    public ArrayList<IRBasicBlock> Dom=new ArrayList<>();
    public int number;
}
