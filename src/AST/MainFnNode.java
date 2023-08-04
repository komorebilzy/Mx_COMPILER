package AST;
import AST.ASTNode;
import AST.ASTVisitor;
import AST.Stmt.StmtNode;
import Util.*;
import java.util.ArrayList;


public class MainFnNode extends ASTNode {
    public ArrayList<StmtNode> stmts;

    public MainFnNode(position pos) {
        super(pos);
        stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
