package AST.Expr;

import AST.ASTVisitor;
import Util.position;
public class BinaryExprNode extends ExprNode{
    public String op;
    public ExprNode lhs,rhs;

    public BinaryExprNode(position pos){
        super(pos);
    }

    public BinaryExprNode(position pos,String op,ExprNode lhs,ExprNode rhs){
        super(pos);
        this.op=op;
        this.lhs=lhs;
        this.rhs=rhs;
    }

    @Override
    public boolean isAssignable(){return false;}
    public boolean isLeftValue() {
        return true;
    }
    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
