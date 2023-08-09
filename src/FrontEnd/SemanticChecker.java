package FrontEnd;

import AST.ASTVisitor;
import AST.Def.*;
import AST.Expr.*;
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
        if (mainFunc == null || !mainFunc.returnType.typeName.equals("int") || mainFunc.params != null) {
            throw new semanticError("the main function is wrong", it.pos);
        }
        //总程序是顺序访问，所以前面的function不可以调用后面的variable
        for (var def : it.DefList) {
            def.accept(this);
        }
    }


    //DefNode
    public void visit(ClassDefNode it) {
        currentScope = new Scope(currentScope, it);
        //先遍历所有的var，使得前面的func也可以调用后面的variable！！
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
        if (!it.returnType.typeName.equals("int") && !it.returnType.typeName.equals("bool") && !it.returnType.typeName.equals("void") && !it.returnType.typeName.equals("string") && globalScope.getClass(it.returnType.typeName) == null) {
            throw new semanticError("undefined type of function", it.pos);
        }
        currentScope = new Scope(currentScope, it.returnType);
//        if(currentScope.parentScope.inWhichClass!=null) currentScope.inWhichClass=currentScope.parentScope.inWhichClass;
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
            throw new semanticError("undefined type of " + it.type.typeName, it.pos);
        }
        if (it.init != null) it.init.accept(this);
        if (it.init!=null && !NullType.equals(it.init.type) && it.type.dim != it.init.type.dim)
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
            //debug:the condition: int [][]a=new int[1][2],int []b=new int [1];int c=b[a];
            if (it.index.get(i).type == null || !it.index.get(i).type.equals(IntType))
                throw new semanticError("invalid index of array", it.pos);
        }
        //debug : array[0][1]:dim=0   array[1]:dim=1;
        it.type = new Type(it.name.type);
        it.type.dim -= it.index.size();
        if (it.type.dim < 0)
            throw new semanticError("the dim of the variable is more than it can be", it.pos);
    }


    public void visit(AtomExprNode it) {
        if (it.str.equals("true") || it.str.equals("false")) it.type = BoolType;
        else if (it.str.equals("null")) it.type = NullType;
        else if (it.str.equals("this")) {
            if (currentScope.inWhichClass == null) throw new semanticError("this is not in a Scope or class", it.pos);
            it.type = new Type(currentScope.inWhichClass.name);
            //debug:boolean matches(String regex)：用于检测字符串是否为对应的正则表达式
        } else if (it.str.matches("\".*\"")) {
            it.type = StringType;
        } else if (it.type != null && it.type.typeName.equals("int")) it.type = IntType;
        else {
            boolean flag = false;
            for (Scope i = currentScope; i != null; i = i.parentScope) {
                if (i.varMembers.containsKey(it.str)) {
                    it.type = i.varMembers.get(it.str);
                    it.isLeft=true;
                    flag = true;
                    break;
                }
                else if(i.inWhichClass!=null && i.inWhichClass.funcMem.containsKey(it.str)){
                    it.type=new Type(i.inWhichClass.funcMem.get(it.str));
                    flag=true;
                    break;
                }
                if (i instanceof GlobalScope) {
                    if (((GlobalScope) i).getClass(it.str) != null) {
                        it.type = new Type(((GlobalScope) i).getClass(it.str).name);
                        flag = true;
                        break;
                    } else if (((GlobalScope) i).getFunc(it.str) != null) {
                        it.type = new Type(((GlobalScope) i).getFunc(it.str));
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) throw new semanticError("the variable is not defined before", it.pos);
        }
    }


    public void visit(UnaryExprNode it) {
        it.expr.accept(this);
        if (it.expr.type == null) throw new semanticError("invalid expression", it.pos);
        if (it.op.equals("++") || it.op.equals("--")) {
            //对于变量，我们在前面遍历了expr 已经改变了他的isLeft
            if (!it.expr.isLeftValue() || !it.expr.type.typeName.equals("int"))
                throw new semanticError("it is not a left value", it.pos);
            it.type = IntType;
        } else if (it.op.equals("!")) {
            if (!it.expr.type.equals(BoolType)) throw new semanticError("Type should be bool", it.pos);
            it.type = BoolType;
        } else {
            if (!it.expr.type.typeName.equals("int")) throw new semanticError("Type should be int", it.pos);
            it.type = IntType;
        }
    }

    public void visit(PreAddExprNode it) {
        it.expr.accept(this);
        if (it.expr.type == null || !it.expr.isLeftValue()) throw new semanticError("invalid expression", it.pos);
        if (!it.expr.type.equals(IntType)) throw new semanticError("Type should be int", it.pos);
        it.type = IntType;
    }

    public void visit(BinaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type == null || it.rhs.type == null || it.rhs.type.equals(VoidType))
            throw new semanticError("invalid type of binaryExpression", it.pos);
        if (!it.lhs.type.equals(it.rhs.type)) {
            //debug:the condition-- 类!=null
            if ((it.op.equals("==") || it.op.equals("!=")) && (it.lhs.type.dim > 0||it.lhs.type.isClass) && it.rhs.type.equals(NullType)) {
                it.type = BoolType;
                return;
            } else throw new semanticError("invalid type of binaryExpression", it.pos);
        }
        switch (it.op) {
            case "*", "/", "%", "-", "<<", ">>", "|", "^", "&" -> {
                if (!it.lhs.type.equals(IntType))
                    throw new semanticError("the type should be int", it.pos);
                it.type = IntType;
            }
            case "+", "<=", ">=", "<", ">" -> {
                if (!it.lhs.type.equals(IntType) && !it.lhs.type.equals(StringType))
                    throw new semanticError("the type should be int ot string", it.pos);
                it.type = it.op.equals("+") ? new Type(it.lhs.type) : BoolType;
            }
            case "==","!=" ->{
                it.type=BoolType;
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
            //debug: 类=null也可以
            if (!it.rhs.type.equals(NullType) || !(it.lhs.type.dim>0 || it.lhs.type.isClass))
                throw new semanticError("type does not match", it.pos);
        }
        //debug:the condition true=false
        if (!it.lhs.isLeftValue())
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


    //mainly debug
    public void visit(FuncExprNode it) {
        it.funcName.accept(this);
        if (it.funcName instanceof MemExprNode) {
            it.type = it.funcName.type;
            if (it.lists != null) {
                it.lists.accept(this);
                ArrayList<VaraDefUnitNode> a = ((MemExprNode) it.funcName).funcMem.params.varList;
                if (a.size() != it.lists.exprs.size()) throw new semanticError("function does not match", it.pos);
                for (int i = 0; i < a.size(); ++i) {
                    if (!a.get(i).type.equals(it.lists.exprs.get(i).type))
                        throw new semanticError("function does not match", it.pos);
                }
            } else {
                var tmp= ((MemExprNode) it.funcName).funcMem.params;
                if(tmp!=null && tmp.varList.size()!=0)
                    throw new semanticError("function does not match", it.pos);
            }
        } else {
            if (it.lists != null) it.lists.accept(this);
            FuncDefNode compare = new FuncDefNode(null);
            if (currentScope.inWhichClass != null && currentScope.inWhichClass.funcMem.containsKey(it.funcName.str)) {
                compare = currentScope.inWhichClass.funcMem.get(it.funcName.str);
            } else {
                compare = globalScope.getFunc(it.funcName.str);
            }
            if (it.lists == null) {
                if (!((compare.params == null || compare.params.varList.size() == 0)))
                    throw new semanticError("the type of function params does not match", it.pos);
            } else {
                if (compare.params == null || compare.params.varList.size() != it.lists.exprs.size())
                    throw new semanticError("the type of function params does not match", it.pos);
                ArrayList<VaraDefUnitNode> com=compare.params.varList;
                ArrayList<ExprNode> my=it.lists.exprs;
                for (int i = 0; i < com.size(); ++i) {
                    if (!com.get(i).type.equals(my.get(i).type) && !(com.get(i).type.isClass && my.get(i).type.equals(NullType)))
                        throw new semanticError("the type of function params does not match", it.pos);
                }
            }
            it.type = compare.returnType;
        }

    }

    public void visit(MemExprNode it) {
        it.className.accept(this);
        if (it.className.type == null) throw new semanticError("invalid expression", it.pos);
        //debug:not only the member of class,but can also the member of array,like: array a.size()
        if (it.className.type.dim > 0 && it.member.equals("size")) {
            it.type = IntType;
            it.funcMem=Size;
            return;
        }
        if (globalScope.getClass(it.className.type.typeName) == null)
            throw new semanticError("className does not exist", it.pos);
        ClassDefNode a = globalScope.getClass(it.className.type.typeName);
        if (a.varMem.containsKey(it.member)) {
            it.type = a.varMem.get(it.member).type;
            if(it.type.dim>0) it.funcMem=Size;
        } else if (a.funcMem.containsKey(it.member)) {
            it.funcMem = a.funcMem.get(it.member);
            it.type = it.funcMem.returnType;
        } else throw new semanticError("the class does not contain the member or function", it.pos);


    }

    public void visit(NewExprNode it) {
        boolean isEmpty = false;
        for (var expr : it.lists) {
            if (expr == null) isEmpty = true;
            else if(isEmpty) throw new semanticError("array dimension can not be empty",it.pos);
            else {
                expr.accept(this);
                if(!expr.type.equals(IntType)) throw new semanticError("the index type is wrong",it.pos);
            }
        }
        if (!it.typeName.equals("int") && !it.typeName.equals("bool") && !it.typeName.equals("string") && globalScope.getClass(it.typeName) == null) {
            throw new semanticError("the type does not exist", it.pos);
        }
        it.type = new Type(it.typeName, it.dim);
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
                    if (!theScope.returnType.equals(it.returnExpr.type) && !(it.returnExpr.type.equals(NullType) && theScope.returnType.isClass))
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
