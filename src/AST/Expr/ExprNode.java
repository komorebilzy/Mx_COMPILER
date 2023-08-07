package AST.Expr;

import AST.ASTNode;
import Util.Type;
import Util.position;

public abstract class ExprNode extends ASTNode {
    public String str;
    public Type type;


    public ExprNode(position pos){
        super(pos);
    }

    public abstract boolean isAssignable();
    public abstract boolean isLeftValue();

}
