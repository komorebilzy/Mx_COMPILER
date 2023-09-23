package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;

import java.util.ArrayList;

public class storeInst extends IRInst{
    public IREntity pointer;
    public IREntity value;

    public storeInst(IRBasicBlock par, IREntity pointer, IREntity value){
        super(par);
        this.pointer=pointer;
        this.value=value;
        for(var inst:par.inFunc.allocas){
            if(inst.res.equals(pointer)){
                if(!par.inFunc.storeIns.containsKey(pointer.getValue())){
                    par.inFunc.storeIns.put(pointer.getValue(),new ArrayList<>());
                }
                par.inFunc.storeIns.get(pointer.getValue()).add(this);
            }
        }
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "store "+value.toString()+", ptr "+pointer.getValue();
    }
}
