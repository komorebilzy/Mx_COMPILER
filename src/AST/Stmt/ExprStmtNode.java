package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;

public class ExprStmtNode extends StmtNode{
    public ExprNode expr;
    public ExprStmtNode(ExprNode expr, position pos) {
        super(pos);
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
