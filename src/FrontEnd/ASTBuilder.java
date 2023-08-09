package FrontEnd;

import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;
import Parser.MxParser;
import Parser.MxBaseVisitor;
import Util.Error.*;
import Util.position;
import Util.Type;
import AST.*;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

    public ASTBuilder() {
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));
        for (var x : ctx.children) {
            if (x instanceof MxParser.ClassDefContext) {
                root.DefList.add((ClassDefNode) visit(x));
            } else if (x instanceof MxParser.FuncDefContext) {
                root.DefList.add((FuncDefNode) visit(x));
            } else if (x instanceof MxParser.VarDefContext) {
                root.DefList.add((VarDefNode) visit(x));
            }
        }
        return root;
    }


    //DEF
    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        VarDefNode varDef = new VarDefNode(new position(ctx));
        Type type = new Type(ctx.type().typeName().getText());
        type.dim = ctx.type().LBracket().size();
        for (var x : ctx.varDefUnit()) {
            VaraDefUnitNode it = new VaraDefUnitNode(new position(ctx), type, x.Identifier().getText());
            if (x.expr() != null) {
                it.init = (ExprNode) visit(x.expr());
            }
            varDef.units.add(it);
        }
        return varDef;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        FuncDefNode funcDef = new FuncDefNode(new position(ctx), ctx.Identifier().getText());
        Type returnType;
        if (ctx.returnType().Void() != null) returnType = new Type(ctx.returnType().getText());
        else {
            returnType = new Type(ctx.returnType().type().typeName().getText());
            returnType.dim = ctx.returnType().type().LBracket().size();
            returnType.isFunc = true;
        }
        funcDef.returnType = returnType;
        if (ctx.parameterList() != null)
            funcDef.params = (ParameterListNode) visit(ctx.parameterList());
        funcDef.stmts = ((SuiteNode) visit(ctx.suite())).stmts;
        return funcDef;
    }

    @Override
    public ASTNode visitParameterList(MxParser.ParameterListContext ctx) {
        ParameterListNode params = new ParameterListNode(new position(ctx));
        for (int i = 0; i < ctx.type().size(); ++i) {
            VaraDefUnitNode it = new VaraDefUnitNode(new position(ctx));
            it.type = new Type(ctx.type(i).typeName().getText(), ctx.type(i).LBracket().size());
            it.varName = ctx.Identifier(i).getText();
            params.varList.add(it);
        }
        return params;
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        ClassDefNode classDef = new ClassDefNode(new position(ctx), ctx.Identifier().getText());
        if (ctx.classBuild().size() > 1)
            throw new syntaxError("Multiple Constructors", new position(ctx));
        else {
            if (ctx.classBuild().size() == 1) {
                classDef.classBuilder = (ClassBuildNode) visit(ctx.classBuild(0));
            } else classDef.classBuilder = null;
            ctx.varDef().forEach(vd -> classDef.varList.add((VarDefNode) visit(vd)));
            ctx.funcDef().forEach(fd -> classDef.funcList.add((FuncDefNode) visit(fd)));
            return classDef;
        }
    }

    @Override
    public ASTNode visitClassBuild(MxParser.ClassBuildContext ctx) {
        return new ClassBuildNode(new position(ctx), ctx.Identifier().getText(), (SuiteNode) visit(ctx.suite()));
    }


    //EXPR
    @Override
    public ASTNode visitParenExpr(MxParser.ParenExprContext ctx) {
        return (ExprNode) visit(ctx.expr());
    }

    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        NewExprNode newExpr = new NewExprNode(new position(ctx), ctx.typeName().getText());
        newExpr.dim = ctx.newArrayUnit().size();
        newExpr.type = new Type(newExpr.typeName, newExpr.dim);
        for (int i = 0; i < newExpr.dim; ++i) {
            if (ctx.newArrayUnit(i).expr() != null) newExpr.lists.add((ExprNode) visit(ctx.newArrayUnit(i).expr()));
            else newExpr.lists.add(null);
        }
        return newExpr;
    }

    @Override
    public ASTNode visitMemberExpr(MxParser.MemberExprContext ctx) {
        return new MemExprNode(new position(ctx), (ExprNode) visit(ctx.expr()), ctx.Identifier().getText());
    }

    @Override
    public ASTNode visitArrayExpr(MxParser.ArrayExprContext ctx) {
        ArrayExprNode arrExpr = new ArrayExprNode(new position(ctx), (ExprNode) visit(ctx.expr(0)));
        for (int i = 1; i < ctx.expr().size(); ++i) {
            arrExpr.index.add((ExprNode) visit(ctx.expr(i)));
        }
        return arrExpr;
    }

    @Override
    public ASTNode visitFuncExpr(MxParser.FuncExprContext ctx) {
        FuncExprNode funcExpr = new FuncExprNode(new position(ctx));
        funcExpr.funcName = (ExprNode) visit(ctx.expr());
        if (ctx.exprList() != null) funcExpr.lists = (BlockExprNode) visit(ctx.exprList());
        return funcExpr;
    }

    @Override
    public ASTNode visitExprList(MxParser.ExprListContext ctx) {
        BlockExprNode lists = new BlockExprNode(new position(ctx));
        ctx.expr().forEach(x -> lists.exprs.add((ExprNode) visit(x)));
        return lists;
    }

    @Override
    public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
        return new UnaryExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitPreAddExpr(MxParser.PreAddExprContext ctx) {
        return new PreAddExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        return new BinaryExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr(0)), (ExprNode) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitTernaryExpr(MxParser.TernaryExprContext ctx) {
        TernaryExprNode ternaryExpr = new TernaryExprNode(new position(ctx));
        ternaryExpr.judge = (ExprNode) visit(ctx.expr(0));
        ternaryExpr.trueCond = (ExprNode) visit(ctx.expr(1));
        ternaryExpr.falseCon = (ExprNode) visit(ctx.expr(2));
        return ternaryExpr;
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        return new AssignExprNode(new position(ctx), (ExprNode) visit(ctx.expr(0)), (ExprNode) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.Identifier() != null) return new AtomExprNode(new position(ctx), ctx.Identifier().getText());
        else {
            AtomExprNode atom = new AtomExprNode(new position(ctx), ctx.getText());
            if (ctx.IntConst() != null) atom.type = new Type("int");
            else if (ctx.StringConst() != null) atom.type = new Type("string");
            else if (ctx.True() != null) atom.type = new Type("true");
            else if (ctx.False() != null) atom.type = new Type("false");
            else if (ctx.Null() != null) atom.type = new Type("null");
            else if (ctx.This() != null) atom.type = new Type("this");
            return atom;
        }
    }

    //STMT
    public ASTNode visitStatement(MxParser.StatementContext ctx) {
        if (ctx.suite() != null)
            return visit(ctx.suite());
        else if (ctx.varDef() != null)
            return new DefStmtNode((VarDefNode)visit(ctx.varDef()),new position(ctx));
        else if (ctx.exprStmt() != null)
            return visit(ctx.exprStmt());
        else if (ctx.ifStmt() != null)
            return visit(ctx.ifStmt());
        else if (ctx.forStmt() != null)
            return visit(ctx.forStmt());
        else if (ctx.whileStmt() != null)
            return visit(ctx.whileStmt());
        else if (ctx.returnStmt() != null)
            return visit(ctx.returnStmt());
        else if (ctx.breakStmt() != null)
            return visit(ctx.breakStmt());
        else if (ctx.continueStmt() != null)
            return visit(ctx.continueStmt());
        else
            return visitChildren(ctx);
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        SuiteNode suite = new SuiteNode(new position(ctx));
        for (int i = 0; i < ctx.statement().size(); ++i) {
//            if (ctx.statement(i).varDef() != null) {
//                DefStmtNode tmp = new DefStmtNode((VarDefNode) visit(ctx.statement(i)), new position(ctx));
//                suite.stmts.add(tmp);
//            } else suite.stmts.add((StmtNode) visit(ctx.statement(i)));
            suite.stmts.add((StmtNode)visit(ctx.statement(i)));
        }
        return suite;
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        IfStmtNode ifStmt = new IfStmtNode(new position(ctx), (ExprNode) visit(ctx.expr()));
        if (ctx.statement(0).suite() != null) ifStmt.trueCon = ((SuiteNode) visit(ctx.statement(0))).stmts;
        else ifStmt.trueCon.add((StmtNode) visit(ctx.statement(0)));
        if (ctx.statement(1) != null) {
            if (ctx.statement(1).suite() != null) ifStmt.falseCon = ((SuiteNode) visit(ctx.statement(1))).stmts;
            else ifStmt.falseCon.add((StmtNode) visit(ctx.statement(1)));
        }
        return ifStmt;
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        WhileStmtNode whileNode = new WhileStmtNode(new position(ctx), (ExprNode) visit(ctx.expr()));
        if (ctx.statement().suite() != null) whileNode.stmts = ((SuiteNode) visit(ctx.statement().suite())).stmts;
        else whileNode.stmts.add((StmtNode) visit(ctx.statement()));
        return whileNode;
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        ForStmtNode forStmt = new ForStmtNode(new position(ctx));
        if (ctx.forInit().varDef() != null) {
            forStmt.varDef = (VarDefNode) visit(ctx.forInit().varDef());
        } else if (ctx.forInit().exprStmt() != null) {
            //debug: note the condition that ctx.forInit().exprStmt()==null
            forStmt.init = ((ExprStmtNode) visit(ctx.forInit().exprStmt())).expr;
        }
        if (ctx.exprStmt().expr() != null) forStmt.condition = (ExprNode) visit(ctx.exprStmt().expr());
        if (ctx.expr() != null) forStmt.step = (ExprNode) visit(ctx.expr());
        if (ctx.statement().suite() != null) forStmt.stmts = ((SuiteNode) visit(ctx.statement())).stmts;
        else forStmt.stmts.add((StmtNode) visit(ctx.statement()));
        return forStmt;
    }

    @Override
    public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        return new ExprStmtNode(ctx.expr() == null ? null : (ExprNode) visit(ctx.expr()), new position(ctx));
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new BreakStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new ContinueStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ReturnStmtNode returnStmt = new ReturnStmtNode(new position(ctx));
        if (ctx.expr() != null) {
            returnStmt.returnExpr = (ExprNode) visit(ctx.expr());
        } else returnStmt.returnExpr = null;
        return returnStmt;
    }

}











