package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class AtomExprNode extends ExprNode{
    public AtomExprNode(position pos){
        super(pos);
    }
    public boolean isLeft=false;

    public AtomExprNode(position pos, String s){
        super(pos);
        this.str = s;
    }

    @Override
    public boolean isAssignable(){
        return false;
    }

    public boolean isLeftValue() {
        return isLeft;
    }
    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
