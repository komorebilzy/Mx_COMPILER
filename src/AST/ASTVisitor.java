package AST;

import AST.Expr.*;
import AST.Stmt.*;
import AST.Def.*;

public interface ASTVisitor {
    public void visit(RootNode it);
    public void visit(MainFnNode it);

    //DefNode
    public void visit(VarDefNode it);
    public void visit(VaraDefUnitNode it);
    public void visit(FuncDefNode it);
    public void visit(ParameterListNode it);
    public void visit(ClassBuildNode it);
    public void visit(ClassDefNode it);

    //ExprNode
    public void visit(ArrayExprNode it);
    public void visit(AssignExprNode it);
    public void visit(AtomExprNode it);
    public void visit(BinaryExprNode it);
    public void visit(BlockExprNode it);
    public void visit(FuncExprNode it);
    public void visit(MemExprNode it);
    public void visit(NewExprNode it);
    public void visit(PreAddExprNode it);
    public void visit(TernaryExprNode it);
    public void visit(UnaryExprNode it);

    //StmtNode
    public void visit(BreakStmtNode it);
    public void visit(ContinueStmtNode it);
    public void visit(ExprStmtNode it);
    public void visit(ForStmtNode it);
    public void visit(IfStmtNode it);
    public void visit(ReturnStmtNode it);
    public void visit(SuiteNode it);
    public void visit(WhileStmtNode it);
    public void visit(DefStmtNode it);

}
