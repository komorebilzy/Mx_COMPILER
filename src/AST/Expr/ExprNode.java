package AST.Expr;

import AST.ASTNode;
import IR.Entity.IREntity;
import IR.Inst.IRInst;
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

    //用于判断是否需要添加load指令 如果是被assign 则不需要 否则需要再创建一个新的register
    public boolean be_assigned=false;
    public boolean be_built=false;
    public IRInst built_func=null;


}
