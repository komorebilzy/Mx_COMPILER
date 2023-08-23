package AST.Def;

import AST.ASTVisitor;
import Util.position;
import Util.Type;
import AST.Stmt.StmtNode;

import java.util.ArrayList;

public class FuncDefNode extends DefNode {
    public Type returnType;
    public String funcName;
    public ParameterListNode params;
    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public FuncDefNode(position pos) {
        super(pos);
    }

    public FuncDefNode(position pos, String name) {
        super(pos);
        this.funcName = name;
    }

    public FuncDefNode(position pos, String name, Type type, ParameterListNode params) {
        super(pos);
        this.returnType = type;
        this.funcName = name;
        this.params = params;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }


    //=============================IR========================
    public boolean isMain=false;
}


