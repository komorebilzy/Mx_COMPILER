package IR.Inst;

import IR.Entity.IREntity;
import IR.IRBasicBlock;
import IR.IRVisitor;
import Util.BuiltinElements;

public class retInst extends IRInst{
    public IREntity value;

    public retInst(IRBasicBlock par, IREntity value){
        super(par);
        this.value=value;
    }

    public retInst(IRBasicBlock par){
        super(par);
        this.value= BuiltinElements.irVoidConst;
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ret "+value.toString();
    }
}
