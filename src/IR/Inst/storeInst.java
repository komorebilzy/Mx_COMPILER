package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;

public class storeInst extends IRInst{
    public IREntity pointer;
    public IREntity value;

    public storeInst(IRBasicBlock par, IREntity pointer, IREntity value){
        super(par);
        this.pointer=pointer;
        this.value=value;
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
