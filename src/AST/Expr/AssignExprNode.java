package AST.Expr;

import AST.ASTVisitor;
import Util.position;

import javax.naming.BinaryRefAddr;

public class AssignExprNode extends BinaryExprNode {
    public AssignExprNode(position pos){
        super(pos);
    }

    public AssignExprNode(position pos, ExprNode lhs, ExprNode rhs){
        super(pos, "=", lhs, rhs);
    }

    @Override
    public boolean isAssignable(){
        return true;
    }

    public boolean isLeftValue() {
        return true;
    }
    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
