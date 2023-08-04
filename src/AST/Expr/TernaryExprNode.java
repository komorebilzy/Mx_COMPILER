package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class TernaryExprNode extends ExprNode{
    public ExprNode judge;
    public ExprNode trueCond;
    public ExprNode falseCon;

    public TernaryExprNode(position pos){
        super(pos);
    }

    public TernaryExprNode(position pos,ExprNode judge,ExprNode trueCon,ExprNode falseCon){
        super(pos);
        this.judge=judge;
        this.falseCon=falseCon;
        this.trueCond=trueCon;
    }

    @Override
    public boolean isAssignable(){return false;}

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
