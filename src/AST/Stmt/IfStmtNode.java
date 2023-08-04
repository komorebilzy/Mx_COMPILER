package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;

import java.util.ArrayList;

public class IfStmtNode extends StmtNode{
    public ExprNode judge;
    public ArrayList<StmtNode> trueCon =new ArrayList<>();
    public ArrayList<StmtNode> falseCon=new ArrayList<>();

    public IfStmtNode(position pos){
        super(pos);
    }
    public IfStmtNode(position pos,ExprNode judge){
        super(pos);
        this.judge=judge;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
