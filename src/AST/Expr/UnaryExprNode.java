package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class UnaryExprNode extends ExprNode{
    public String op;
    public ExprNode expr;

    public UnaryExprNode(position pos){
        super(pos);
    }

    public UnaryExprNode(position pos,String op,ExprNode expr){
        super(pos);
        this.op=op;
        this.expr=expr;
    }

    @Override
    public boolean isAssignable(){return false;}

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
