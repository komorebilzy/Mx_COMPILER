package AST.Expr;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class BlockExprNode extends ExprNode{
    public ArrayList<ExprNode> exprs = new ArrayList<>();

    public BlockExprNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
    @Override
    public boolean isAssignable(){return false;}


}
