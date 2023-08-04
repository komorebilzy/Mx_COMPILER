package AST.Def;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class VarDefNode extends DefNode {
    public ArrayList<VaraDefUnitNode> units=new ArrayList<>();
    public VarDefNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
