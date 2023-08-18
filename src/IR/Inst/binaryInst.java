package IR.Inst;

import IR.Entity.IREntity;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

public class binaryInst extends IRInst {
    public String op;
    public IRType resultType;
    public IREntity res, lhs, rhs;


    public binaryInst(IRBasicBlock block, String str, IREntity res,IREntity lhs, IREntity rhs) {
        super(block);
        this.lhs = lhs;
        this.rhs = rhs;
        this.res = res;
        this.resultType=lhs.type;
        switch (str) {
            case "+" -> op = "add";
            case "-" -> op = "sub";
            case "*" -> op = "mul";
            case "/" -> op = "sdiv";
            case "%" -> op = "srem";
            case "<<" -> op = "shl";
            case ">>" -> op = "ashr";
            case "&" -> op = "and";
            case "|" -> op = "or";
            case "^" -> op = "xor";
        }
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
