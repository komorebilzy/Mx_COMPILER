package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;

import java.util.ArrayList;

public class WhileStmtNode extends StmtNode {
    public ExprNode condition;

    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public WhileStmtNode(position pos) {
        super(pos);
    }

    public WhileStmtNode(position pos, ExprNode condition) {
        super(pos);
        this.condition = condition;
    }


    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
