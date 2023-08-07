package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class FuncExprNode extends ExprNode {
    public ExprNode funcName;
    public BlockExprNode lists;

    public FuncExprNode(position pos) {
        super(pos);
    }

    public FuncExprNode(position pos, ExprNode name, BlockExprNode lists) {
        super(pos);
        this.lists = lists;
        this.funcName = name;
    }


    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return false;
    }
    public boolean isLeftValue() {
        return false;
    }

}
