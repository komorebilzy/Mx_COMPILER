package AST.Expr;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class ArrayExprNode extends ExprNode{
    public ExprNode name;
    public ArrayList<ExprNode> index=new ArrayList<>();

    public ArrayExprNode(position pos){
        super(pos);
    }

    public ArrayExprNode(position pos, ExprNode arrayName){
        super(pos);
        this.name = arrayName;
    }

    @Override
    public boolean isAssignable(){
        return true;
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }


}
