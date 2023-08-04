package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class MemExprNode extends ExprNode{
    public ExprNode className;
    public String member;

    public MemExprNode(position pos){
        super(pos);
    }

    public MemExprNode(position pos, ExprNode name, String mem){
        super(pos);
        this.className = name;
        this.member = mem;
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
