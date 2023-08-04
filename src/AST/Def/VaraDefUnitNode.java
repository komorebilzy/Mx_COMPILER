package AST.Def;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;
import Util.Type;

public class VaraDefUnitNode extends DefNode {
    public Type type;
    public String varName;
    public ExprNode init;

    public VaraDefUnitNode(position pos) {
        super(pos);
    }

    public VaraDefUnitNode(position pos, Type type, String name) {
        super(pos);
        this.varName = name;
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
