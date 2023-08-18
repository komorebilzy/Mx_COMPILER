package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;

public class storeInst extends IRInst{
    public IREntity value;
    public IREntity res;

    public storeInst(IRBasicBlock par, IREntity value, IREntity res){
        super(par);
        this.value=value;
        this.res=res;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "store "+res.toString()+", "+value.toString();
    }
}
