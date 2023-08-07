package FrontEnd;

import AST.ASTVisitor;
import AST.Def.*;
import AST.Expr.*;
import AST.MainFnNode;
import AST.RootNode;
import AST.Stmt.*;
import Util.Scope.*;
import Util.BuiltinElements;
import Util.Error.*;
import Util.Type;

import java.util.ArrayList;


public class SemanticChecker implements ASTVisitor, BuiltinElements {
    public GlobalScope globalScope;
    public Scope currentScope;

    public SemanticChecker(GlobalScope gScope) {
        this.globalScope = gScope;
        this.currentScope = gScope;
    }

    public void visit(RootNode it) {
        FuncDefNode mainFunc = globalScope.getFunc("main");
        if (mainFunc == null || mainFunc.returnType != IntType || mainFunc.params != null) {
            throw new semanticError("the main function is wrong", it.pos);
        }
        for (var def : it.DefList) {
            def.accept(this);
        }
    }

    public void visit(MainFnNode it) {

    }

    //DefNode
    public void visit(ClassDefNode it) {
        currentScope = new Scope(currentScope, it);
        it.varList.forEach(var -> var.accept(this));
        if (it.classBuilder != null) {
            if (it.classBuilder.name.equals(it.name)) {
                it.classBuilder.accept(this);
            } else throw new semanticError("builder_name doesn't match the class name", it.classBuilder.pos);
        }
        it.funcList.forEach(func -> func.accept(this));
        currentScope = currentScope.parentScope;
    }

    public void visit(ClassBuildNode it) {
        currentScope = new Scope(currentScope, VoidType);
        it.suites.accept(this);
        currentScope = currentScope.parentScope;
    }

    public void visit(FuncDefNode it) {
        //judge the returnType
        if (it.returnType != IntType && it.returnType != VoidType && it.returnType != BoolType && it.returnType != StringType && globalScope.getClass(it.returnType.typeName) != null) {
            throw new semanticError("undefined type of function", it.pos);
        }
        currentScope = new Scope(currentScope, it.returnType);
        if (it.params != null) it.params.accept(this);
        it.stmts.forEach(stmt -> stmt.accept(this));
        //此时已经跑完returnStmt isReturned已经被标记成功
        if (!VoidType.equals(it.returnType) && !it.funcName.equals("main") && !currentScope.isReturned) {
            throw new semanticError("don't have return of a function", it.pos);
        }
        currentScope = currentScope.parentScope;
    }

    public void visit(ParameterListNode it) {
        it.varList.forEach(unit -> unit.accept(this));
    }

    public void visit(VarDefNode it) {
        it.units.forEach(unit -> unit.accept(this));
    }

    public void visit(VaraDefUnitNode it) {
        if (!it.type.typeName.equals("int") && !it.type.typeName.equals("bool") && !it.type.typeName.equals("string") && globalScope.getClass(it.type.typeName) == null) {
            throw new semanticError("undefined type of variable", it.pos);
        }
        if (it.init != null) it.init.accept(this);
        if (it.init != null && it.type.dim != it.init.type.dim)
            throw new semanticError("the dim of array is wrong ", it.pos);
        if (currentScope.hasValInThisScope(it.varName)) {
            throw new semanticError("redefinition of variable " + it.varName, it.pos);
        }
        currentScope.addVar(it.varName, it.type, it.pos);
    }


    //ExprNode
    public void visit(ArrayExprNode it) {
        it.name.accept(this);
        it.index.forEach(in -> in.accept(this));
        if (it.name.type == null) throw new semanticError("invalid expression", it.pos);
        for (int i = 0; i < it.index.size(); ++i) {
            if (it.index.get(i).type == null || !it.index.get(i).type.equals(IntType))
                throw new semanticError("invalid index of array", it.pos);
        }
        //todo : 还没太搞懂dim的作用
        it.type = new Type(it.name.type);
        --it.type.dim;
        if (it.type.dim < 0)
            throw new semanticError("type does not match", it.pos);
    }


    public void visit(AtomExprNode it) {
        if (it.str.equals("true") || it.str.equals("false")) it.type = BoolType;
        else if (it.str.equals("null")) it.type = NullType;
        else if (it.str.equals("this")) {
            if (currentScope.inWhichClass == null) throw new semanticError("this is not in a Scope or class", it.pos);
            it.type = new Type(currentScope.inWhichClass.name);
        } else if (it.str.equals("\".*\"")) {
            it.type = StringType;
        } else if (it.str.equals("int")) it.type = IntType;
        else throw new semanticError("it is not expected to exist", it.pos);
    }


    public void visit(UnaryExprNode it) {
        it.expr.accept(this);
        if (it.expr.type == null) throw new semanticError("invalid expression", it.pos);
        if (it.op.equals("++") || it.op.equals("--")) {
            if (!it.expr.isLeftValue() || !it.expr.type.equals(IntType))
                throw new semanticError("it is not a left value", it.pos);
            it.type = IntType;
        } else if (it.op.equals("!")) {
            if (!it.expr.type.equals(BoolType)) throw new semanticError("Type should be bool", it.pos);
            it.type = BoolType;
        } else {
            if (!it.expr.type.equals(IntType)) throw new semanticError("Type should be int", it.pos);
            it.type = IntType;
        }
    }

    public void visit(PreAddExprNode it) {
        it.expr.accept(this);
        if (it.expr.type == null) throw new semanticError("invalid expression", it.pos);
        if (!it.expr.type.equals(IntType)) throw new semanticError("Type should be int", it.pos);
        it.type = IntType;
    }

    public void visit(BinaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type == null || it.rhs.type == null || !it.lhs.type.equals(it.rhs.type) || it.rhs.type.equals(VoidType))
            throw new semanticError("invalid type of binaryExpression", it.pos);
        switch (it.op) {
            case "*", "/", "%", "-", "<<", ">>", "|", "^", "&" -> {
                if (!it.lhs.type.equals(IntType))
                    throw new semanticError("the type should be int", it.pos);
                it.type = IntType;
            }
            case "+", "<=", ">=", "<", ">" -> {
                if (!it.lhs.type.equals(IntType) || !it.lhs.type.equals(StringType))
                    throw new semanticError("the type should be int ot string", it.pos);
                it.type = it.op.equals("+") ? new Type(it.lhs.type) : BoolType;
            }
            case "==", "!=" -> {
                //todo dim>1代表着为数组
                if (it.lhs.type.dim > 1 && !it.rhs.type.equals(NullType)) {
                    throw new semanticError("the type should be nullType", it.pos);
                }
                it.type = BoolType;
            }
            case "||", "&&" -> {
                if (!it.lhs.type.equals(BoolType))
                    throw new semanticError("the type should be bool", it.pos);
                it.type = BoolType;
            }
        }
    }

    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type == null || it.rhs.type == null || it.rhs.type.equals(VoidType))
            throw new semanticError("invalid type of assignExpression", it.pos);
        if (!it.lhs.type.equals(it.rhs.type)) {
            if (!(it.lhs.type.dim > 1 && it.rhs.type.equals(NullType)))
                throw new semanticError("type does not match", it.pos);
        }
        if (!it.isLeftValue())
            throw new semanticError("it is not a left value", it.pos);
        it.type = it.lhs.type;
    }

    public void visit(TernaryExprNode it) {
        it.judge.accept(this);
        it.trueCond.accept(this);
        it.falseCon.accept(this);
        if (!(it.judge.type.equals(BoolType) && it.trueCond.type.equals(it.falseCon.type)))
            throw new semanticError("type does not match", it.pos);
        it.type = it.trueCond.type;
    }

    public void visit(BlockExprNode it) {
        it.exprs.forEach(expr -> expr.accept(this));
    }

    //todo to be revised
    public void visit(FuncExprNode it) {
        it.funcName.accept(this);
        if (it.funcName instanceof MemExprNode) {
            it.lists.accept(this);
            ArrayList<VaraDefUnitNode> a = ((MemExprNode) it.funcName).funcMem.params.varList;
            if (a.size() != it.lists.exprs.size()) throw new semanticError("function does not match", it.pos);
            for (int i = 0; i < a.size(); ++i) {
                if (a.get(i).type != it.lists.exprs.get(i).type)
                    throw new semanticError("function does not match", it.pos);
            }
        } else {
            boolean flag = false;
            it.lists.accept(this);
            for (Scope tmp = currentScope; tmp != null; tmp = tmp.parentScope) {
                if (flag) break;
                if (tmp.inWhichClass != null) {
                    FuncDefNode a = tmp.inWhichClass.funcMem.get(it.funcName.str);
                    if (a.params.varList.size() == it.lists.exprs.size()) {
                        flag = true;
                        for (int i = 0; i < a.params.varList.size(); ++i) {
                            if (a.params.varList.get(i).type != it.lists.exprs.get(i).type) flag = false;
                        }
                    }
                } else {
                    if (globalScope.getFunc(it.funcName.str) != null) {
                        FuncDefNode b = globalScope.getFunc(it.funcName.str);
                        if (b.params.varList.size() == it.lists.exprs.size()) {
                            flag = true;
                            for (int i = 0; i < b.params.varList.size(); ++i) {
                                if (b.params.varList.get(i).type != it.lists.exprs.get(i).type) flag = false;
                            }
                        }
                    }
                }
            }
            if (!flag)
                throw new semanticError("function does not match", it.pos);
        }

    }

    public void visit(MemExprNode it) {
        it.className.accept(this);
        if (it.className.type == null) throw new semanticError("invalid expression", it.pos);
        if (globalScope.getClass(it.className.str) == null) throw new semanticError("className does not exist", it.pos);
        ClassDefNode a = globalScope.getClass(it.className.str);
        if (!a.varMem.containsKey(it.member) && !a.funcMem.containsKey(it.member))
            throw new semanticError("the class does not contain the member or function", it.pos);
        it.funcMem = a.funcMem.get(it.member);
    }

    public void visit(NewExprNode it) {
        if (it.typeName.equals("int")) it.type = IntType;
        else if (it.typeName.equals("bool")) it.type = BoolType;
        else if (globalScope.getClass(it.typeName) != null) {
            it.type.isClass = true;
            it.type.typeName = it.typeName;
        } else{
            throw new semanticError("the type does not exist",it.pos);
        }
    }


    //StmtNode
    public void visit(BreakStmtNode it) {
        if (!currentScope.inLoop)
            throw new semanticError("break statement is not in the loop", it.pos);
    }

    public void visit(ContinueStmtNode it) {
        if (!currentScope.inLoop)
            throw new semanticError("continue statement is not in the loop", it.pos);
    }

    public void visit(ExprStmtNode it) {
        if (it.expr != null) it.expr.accept(this);
    }

    public void visit(ForStmtNode it) {
        currentScope = new Scope(currentScope, true);
        if (it.varDef != null) it.varDef.accept(this);
        if (it.init != null) it.init.accept(this);
        if (it.condition != null) {
            it.condition.accept(this);
            if (!it.condition.type.equals(BoolType))
                throw new semanticError("the condition expression of forstmt is wrong", it.pos);
        }
        if (it.step != null) it.step.accept(this);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
    }

    public void visit(IfStmtNode it) {
        it.judge.accept(this);
        if (!it.judge.type.equals(BoolType))
            throw new semanticError("the condition expression of ifStmt is wrong", it.pos);
        currentScope = new Scope(currentScope);
        it.trueCon.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
        if (it.falseCon != null) {
            currentScope = new Scope(currentScope);
            it.falseCon.forEach(stmt -> stmt.accept(this));
            currentScope = currentScope.parentScope;
        }
    }

    public void visit(ReturnStmtNode it) {
        for (var theScope = currentScope; theScope != null; theScope = theScope.parentScope) {
            if (theScope.returnType != null) {
                if (it.returnExpr == null) {
                    if (!theScope.returnType.equals(VoidType))
                        throw new semanticError("return type does not match", it.pos);
                } else {
                    it.returnExpr.accept(this);
                    if (!theScope.returnType.equals(it.returnExpr.type))
                        //todo : we have ignored the returnType a[]
                        throw new semanticError("return type does not match", it.pos);
                }
                theScope.isReturned = true;
                return;
            }
        }
        throw new semanticError("return statement is outside the function", it.pos);
    }

    public void visit(SuiteNode it) {
        currentScope = new Scope(currentScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
    }

    public void visit(WhileStmtNode it) {
        it.condition.accept(this);
        if (!it.condition.type.equals(BoolType))
            throw new semanticError("the condition expression of whileStmt is wrong", it.pos);
        currentScope = new Scope(currentScope, true);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
    }

    public void visit(DefStmtNode it) {
        it.def.accept(this);
    }
}
