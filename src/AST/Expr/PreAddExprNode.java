package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class PreAddExprNode extends UnaryExprNode{
    public PreAddExprNode(position pos){
        super(pos);
    }

    public PreAddExprNode(position pos, String op, ExprNode expr){
        super(pos, op, expr);
    }

    @Override
    public boolean isAssignable() {
        return true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
