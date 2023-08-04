package AST.Stmt;

import AST.ASTVisitor;
import AST.Def.VarDefNode;
import Util.position;

public class DefStmtNode extends StmtNode{
    public VarDefNode def;
    public DefStmtNode(VarDefNode def, position pos) {
        super(pos);
        this.def = def;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
