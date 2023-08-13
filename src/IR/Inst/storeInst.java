package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;

public class storeInst extends IRInst{
    public IREntity value;
    public IRRegister pointer;

    public storeInst(IRBasicBlock par, IREntity value, IRRegister pointer){
        super(par);
        this.value=value;
        this.pointer=pointer;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "store "+value.toString()+", "+pointer.toString();
    }
}
