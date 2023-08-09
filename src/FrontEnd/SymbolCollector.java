package FrontEnd;

import AST.ASTVisitor;
import AST.Def.*;
import AST.Expr.*;
import AST.RootNode;
import AST.Stmt.*;
import Util.Scope.*;
import Util.Error.*;

public class SymbolCollector implements ASTVisitor {
    public GlobalScope gScope;
    public SymbolCollector(GlobalScope gScope){
        this.gScope=gScope;
    }
    public void visit(RootNode it){
        it.DefList.forEach(def->def.accept(this));
    }
    //DefNode
    public void visit(VarDefNode it){
//        it.units.forEach(unit->unit.accept(this));
    }
    //debug : avoid redefinition of variable
    public void visit(VaraDefUnitNode it){
//        if(gScope.getType(it.varName)!=null){
//            throw new semanticError("redefinition of variable",it.pos);
//        }
//        if(gScope.getFunc(it.varName)!=null){
//            throw new semanticError("it is already defined as a function",it.pos);
//        }
//        gScope.addVar(it.varName,it.type,it.pos);
    }
    public void visit(FuncDefNode it){
        if(this.gScope.getFunc(it.funcName)!=null){
            throw new semanticError("redefinition of function",it.pos);
        }
        if(this.gScope.getClass(it.funcName)!=null){
            throw new semanticError("it is already defined as a function",it.pos);
        }
        gScope.addFunc(it.funcName,it);
    }
    public void visit(ParameterListNode it){}
    public void visit(ClassBuildNode it){}
    public void visit(ClassDefNode it){
        if(this.gScope.getClass(it.name)!=null){
            throw new semanticError("redefinition of class",it.pos);
        }
        if(this.gScope.getFunc(it.name)!=null){
            throw new semanticError("it is already defined as a function",it.pos);
        }
        gScope.addClass(it.name,it);
        for(var fun:it.funcList){
            if (it.funcMem.containsKey(fun.funcName)) {
                throw new semanticError(fun.funcName+" it is already defined as a function",fun.pos);
            }
            it.funcMem.put(fun.funcName, fun);
        }
        for(var vara:it.varList){
            for(var unit:vara.units){
                if(it.varMem.containsKey(unit.varName)){
                    throw new semanticError(unit.varName+" is already defined as a variable",unit.pos);
                }
                it.varMem.put(unit.varName,unit);
            }
        }
    }

    //ExprNode
    public void visit(ArrayExprNode it){}
    public void visit(AssignExprNode it){}
    public void visit(AtomExprNode it){}
    public void visit(BinaryExprNode it){}
    public void visit(BlockExprNode it){}
    public void visit(FuncExprNode it){}
    public void visit(MemExprNode it){}
    public void visit(NewExprNode it){}
    public void visit(PreAddExprNode it){}
    public void visit(TernaryExprNode it){}
    public void visit(UnaryExprNode it){}

    //StmtNode
    public void visit(BreakStmtNode it){}
    public void visit(ContinueStmtNode it){}
    public void visit(ExprStmtNode it){}
    public void visit(ForStmtNode it){}
    public void visit(IfStmtNode it){}
    public void visit(ReturnStmtNode it){}
    public void visit(SuiteNode it){}
    public void visit(WhileStmtNode it){}
    public void visit(DefStmtNode it){}
}
