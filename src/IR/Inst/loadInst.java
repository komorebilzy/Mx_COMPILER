package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

public class loadInst extends IRInst{
    public IRRegister res;
    public IRType type;
    public IREntity pointer;

    public loadInst(IRBasicBlock par, IRRegister res, IREntity point){
        super(par);
        this.res=res;
        this.pointer=point;
        this.type=res.type;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return res.getValue()+" = load "+type.toString()+", ptr "+pointer.getValue();
    }
}
