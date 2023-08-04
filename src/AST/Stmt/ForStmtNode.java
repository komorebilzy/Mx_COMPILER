package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import AST.Def.VarDefNode;
import Util.position;

import java.util.ArrayList;

public class ForStmtNode extends StmtNode{
    public VarDefNode varDef;
    public ExprNode init,condition,step;
    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public ForStmtNode(position pos){
        super(pos);
        this.varDef=null;
        this.init=this.condition=this.step=null;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
