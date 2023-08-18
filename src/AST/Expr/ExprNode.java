package AST.Expr;

import AST.ASTNode;
import IR.Entity.IREntity;
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

    //=======================IR===========================
    public IREntity entity;
    public IREntity destPtr=null;

}
