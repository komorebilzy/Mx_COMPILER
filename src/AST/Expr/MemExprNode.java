package AST.Expr;

import AST.ASTVisitor;
import AST.Def.FuncDefNode;
import Util.position;

public class MemExprNode extends ExprNode{
    public ExprNode className;
    public String member;
    public FuncDefNode funcMem;

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
    public boolean isLeftValue() {
        return true;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
