package IR.Inst;

import IR.IRBasicBlock;
import IR.IRVisitor;

public abstract class IRInst{
    public IRBasicBlock basicBlock;
    public IRInst(IRBasicBlock block){
        basicBlock=block;
    }

    public abstract void accept(IRVisitor visitor);
    public abstract String toString();
}
