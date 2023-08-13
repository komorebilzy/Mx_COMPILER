package IR.Inst;

import IR.Entity.IREntity;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

public class binaryInst extends IRInst {
    public String op;
    public IRType resultType;
    public IREntity res, lhs, rhs;


    public binaryInst(IRBasicBlock block, IRType type, String op, IREntity lhs, IREntity rhs) {
        super(block);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
        this.resultType = type;
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);

    }

    @Override
    public String toString() {
        return res.getValue() + " = " + op + " " + resultType.toString() + " " + lhs.toString() + ", " + rhs.toString();
    }
}
