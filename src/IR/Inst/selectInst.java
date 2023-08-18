package IR.Inst;

import IR.Entity.IREntity;
import IR.IRBasicBlock;
import IR.IRVisitor;
import MiddleEnd.IRBuilder;

public class selectInst extends IRInst {
    public IREntity cond, dest, src1, src2;

    public selectInst(IRBasicBlock par, IREntity dest, IREntity condition, IREntity src1, IREntity src2) {
        super(par);
        this.dest = dest;
        cond = condition;
        this.src1 = src1;
        this.src2 = src2;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return dest.getValue() + " = select i1 " + cond.getValue() + ", " + src1.toString() + ", " + src2.toString()+"\n";
    }
}
