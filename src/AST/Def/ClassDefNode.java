package AST.Def;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassDefNode extends DefNode {
    public String name;
    public ClassBuildNode classBuilder;

    public ArrayList<VarDefNode> varList=new ArrayList<>();
    public ArrayList<FuncDefNode> funcList=new ArrayList<>();
    public HashMap<String,FuncDefNode> funcMem=new HashMap<>();
    public HashMap<String,VaraDefUnitNode> varMem=new HashMap<>();

    public ClassDefNode(position pos){
        super(pos);
    }

    public ClassDefNode(position pos,String name){
        super(pos);
        this.name=name;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
