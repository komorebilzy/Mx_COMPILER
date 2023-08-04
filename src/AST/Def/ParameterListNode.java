package AST.Def;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;
import java.util.ArrayList;

public class ParameterListNode extends DefNode {
    public ArrayList<VaraDefUnitNode> varList=new ArrayList<>();

    public ParameterListNode(position pos){
        super(pos);
    }
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
