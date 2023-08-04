package AST.Expr;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class NewExprNode extends ExprNode{
    public String typeName;
    public int dim=0;
    public ArrayList<ExprNode> lists=new ArrayList<>();

    public NewExprNode(position pos){
        super(pos);
    }

    public NewExprNode(position pos, String typeName) {
        super(pos);
        this.typeName = typeName;
    }

    @Override
    public boolean isAssignable() {
        return false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
