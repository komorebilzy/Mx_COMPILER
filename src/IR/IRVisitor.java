package IR;

import IR.Inst.*;
import IR.Type.IRClassType;

public interface IRVisitor {
    public void visit(IRBasicBlock it);
    public void visit(IRFunction it);
    public void visit(IRProgram it);
    public void visit(IRClassType it);

    public void visit(allocateInst it);
    public void visit(binaryInst it);
    public void visit(brInst it);
    public void visit(callInst it);
    public void visit(jumpInst it);
    public void visit(getelementptrInstr it);
    public void visit(lcmpInst it);
    public void visit(loadInst it);
    public void visit(storeInst it);
    public void visit(phiInst it);
    public void visit(retInst it);
    public void visit(selectInst it);

    public void visit(declareInst it);
}
