package IR;

import IR.Inst.*;

import java.util.LinkedList;

public class IRBasicBlock {
    public String name;
    public LinkedList<IRInst> insts = new LinkedList<>();
    IRFunction inFunc;
    public boolean isFinished = false;

    public IRBasicBlock(IRFunction inFunc){
        this.name=inFunc.getLabel();
        this.inFunc=inFunc;
    }

    public IRBasicBlock(String name){
        this.name=name;
    }

    public String getLabel() {
        return name;
    }

    public void addInst(IRInst inst){
        if(!isFinished) this.insts.add(inst);
        if(inst instanceof jumpInst || inst instanceof brInst) isFinished=true;
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
