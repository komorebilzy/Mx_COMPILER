package IR.Inst;

import IR.IRBasicBlock;
import IR.IRVisitor;

public class jumpInst extends IRInst {
    public IRBasicBlock toBlock;

    public jumpInst(IRBasicBlock par, IRBasicBlock toB){
        super(par);
        this.toBlock=toB;
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "br label "+toBlock.getLabel();
    }
}
