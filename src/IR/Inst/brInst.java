package IR.Inst;

import IR.Entity.IREntity;
import IR.IRBasicBlock;
import IR.IRVisitor;

public class brInst extends IRInst{
    public IREntity cond;
    public IRBasicBlock thenBlock;
    public IRBasicBlock elseBlock;


    public brInst(IRBasicBlock par, IREntity cond, IRBasicBlock thenB, IRBasicBlock elseB){
        super(par);
        this.cond=cond;
        this.thenBlock=thenB;
        this.elseBlock=elseB;
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "br i1 "+cond.getValue()+", label "+thenBlock.getLabel()+", label "+elseBlock.getLabel();
    }
}
