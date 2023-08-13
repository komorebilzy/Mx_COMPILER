package IR;

import IR.Inst.IRInst;

import java.util.LinkedList;

public class IRBasicBlock {
    public String name;
    public LinkedList<IRInst> insts = new LinkedList<>();
    public int loopDepth = 0;
    public boolean isFinished = false;

    public String getLabel() {
        return name;
    }

    public void addInst(IRInst inst){

    }

    public String toString(){
        String ans = name + ":\n";
        for(IRInst inst:insts){
            ans+=" "+inst+"\n";
        }
        return ans;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }


}
