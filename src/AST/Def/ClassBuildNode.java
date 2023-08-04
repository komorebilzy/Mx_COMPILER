package AST.Def;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.Stmt.SuiteNode;
import Util.position;
public class ClassBuildNode extends DefNode {
    public String name;

    public SuiteNode suites;
    public ClassBuildNode(position pos){
        super(pos);
    }

    public ClassBuildNode(position pos,String name,SuiteNode suites){
        super(pos);
        this.name=name;
        this.suites=suites;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
