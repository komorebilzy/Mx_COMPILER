package IR.Inst;

import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

public class allocateInst extends IRInst{
    public IRRegister res;
    public IRType type;

    public allocateInst(IRBasicBlock par, IRRegister res, IRType type){
        super(par);
        this.res=res;
        this.type=type;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return res.getValue()+" = alloca "+type.toString();
    }

}
