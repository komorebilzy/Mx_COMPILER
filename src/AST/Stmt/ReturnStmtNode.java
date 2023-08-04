package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;

public class ReturnStmtNode extends StmtNode {
    public ExprNode returnExpr;

    public ReturnStmtNode(position pos) {
        super(pos);
    }

    public ReturnStmtNode(position pos, ExprNode expr) {
        super(pos);
        this.returnExpr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
