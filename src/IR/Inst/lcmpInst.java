package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

//eq：相等
//ne：不相等
//ugt：无符号大于
//uge：无符号大于等于
//ult：无符号小于
//ule：无符号小于等于
//sgt：有符号大于
//sge：有符号大于等于
//slt：有符号小于
//sle：有符号小于等于

public class lcmpInst extends IRInst {
    public IRRegister res;
    public String cond;
    public IRType type;
    public IREntity op1, op2;

    public lcmpInst(IRBasicBlock par, IRRegister res, String cond, IREntity o1, IREntity o2) {
        super(par);
        this.res = res;
        this.cond = cond;
        this.op1 = o1;
        this.op2 = o2;
        this.type = op1.type;
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return res.getValue()+" = icmp "+cond+" "+op1.toString()+", "+op2.getValue();
    }
}
