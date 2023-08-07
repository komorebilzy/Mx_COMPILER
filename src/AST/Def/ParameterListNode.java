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

    public ParameterListNode(position pos,VaraDefUnitNode it){
        super(pos);
        varList.add(it);
    }

    public ParameterListNode(position pos,VaraDefUnitNode i1,VaraDefUnitNode i2){
        super(pos);
        varList.add(i1);
        varList.add(i2);
    }
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
