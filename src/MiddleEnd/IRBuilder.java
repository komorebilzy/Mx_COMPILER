package MiddleEnd;

import AST.ASTVisitor;
import AST.Def.*;
import AST.Expr.*;
import AST.RootNode;
import AST.Stmt.*;
import IR.Entity.IRConst;
import IR.Entity.IREntity;
import IR.Entity.IRGlobalVal;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRProgram;
import IR.Inst.*;
import IR.Type.*;
import Util.BuiltinElements;
import Util.Error.irError;
import Util.Scope.GlobalScope;
import Util.Scope.Scope;
import Util.Type;

import javax.swing.text.html.parser.Entity;
import java.lang.invoke.TypeDescriptor;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor, BuiltinElements {
    IRFunction currentFunc = null;
    IRClassType currentClass = null;
    IRBasicBlock currentBlock = null;
    GlobalScope globalScope;
    Scope currentScope = null;
    IRProgram root;

    //used for while and for statement
    IRBasicBlock curBeginBlock = null;
    IRBasicBlock curEndBlock = null;

    HashMap<String, IRClassType> structTypeMap = new HashMap<>();
    HashMap<IRRegister, Integer> arraySizeMap = new HashMap<>();

    public IRBuilder(IRProgram root, GlobalScope globalScope) {
        this.root = root;
        this.globalScope = globalScope;
        currentScope = globalScope;
    }

    IRType getIRType(Type type) {
        IRType irType;
        switch (type.typeName) {
            case "int":
                irType = irIntType;
                break;
            case "bool":
                irType = irBoolType;
                break;
            case "string":
                irType = irStringType;
                break;
            case "void":
                irType = irVoidType;
                break;
            default:
                irType = new IRPtrType(structTypeMap.get(type.typeName), 1);
        }
        if (type.dim > 1) {
            irType = new IRPtrType(irType, type.dim);
        }
        return irType;
    }

    //=================================================Definition=======================================================
    @Override
    public void visit(RootNode it) {
        for (int i = 0; i < it.DefList.size(); ++i) {
            if (it.DefList.get(i) instanceof ClassDefNode) {
                it.DefList.get(i).accept(this);
            } else if (it.DefList.get(i) instanceof FuncDefNode) {
                it.DefList.get(i).accept(this);
            } else if (it.DefList.get(i) instanceof VarDefNode) {
                it.DefList.get(i).accept(this);
            }
        }
    }

    @Override
    public void visit(VarDefNode it) {
        it.units.forEach(unit -> unit.accept(this));
    }

    @Override
    public void visit(VaraDefUnitNode it) {
        //globalVara
        if (currentScope == globalScope) {
            if (it.init != null) it.init.accept(this);
            IRGlobalVal gVal = new IRGlobalVal(getIRType(it.type), it.varName);
            //int a=1; int b=a;
            if (it.init instanceof AtomExprNode) {
                var entity = it.init.entity;
                if (((AtomExprNode) it.init).isConst) {
                    gVal.init = entity;
                } else {
                    IRFunction globalInit = IRFunction.globalVarInit();
                    gVal.init = entity.type.defaultValue();
                    IRRegister tmp = new IRRegister(gVal.type, "%0");
                    globalInit.entry.insts.add(0, new loadInst(null, tmp, it.init.entity));
                    globalInit.entry.insts.add(1, new storeInst(null, gVal, tmp));
                    globalInit.entry.insts.add(2, new retInst(null, BuiltinElements.irVoidConst));
                    root.funcList.add(globalInit);
                }
                root.globalVarList.add(gVal);
            }
            //int m ; A m;
            else if (it.init == null) {
                gVal.init = gVal.type.defaultValue();
                root.globalVarList.add(gVal);
            }
            //全局定义数组 int [][]a=new int [][];
            else {//todo:数组

            }
        }
        //register
        else {
            //todo:只要在func中出现的一定是 for(int i=1;i<10;++i){}中的i
            IRRegister register = new IRRegister(getIRType(it.type), currentFunc.getRegId());
            if (it.init != null) {
                it.init.accept(this);
                register.init = it.init.entity;
            }
            //class自定义的vara变量已经处理了 不需要考虑
            currentScope.addVar(it.varName, register);
            currentBlock.addInst(new allocateInst(currentBlock, register, register.type));
        }
    }

    private void addPara(String name, IRRegister reg) {
        currentFunc.params.add(reg);
        IRRegister addr = new IRRegister(reg.type.asPtr(), currentFunc.getRegId());
        currentBlock.addInst(new allocateInst(currentBlock, addr, reg.type));
        currentBlock.addInst(new storeInst(currentBlock, reg, addr));
        currentScope.addVar(name, addr);
    }

    @Override
    public void visit(FuncDefNode it) {
        currentScope = new Scope(currentScope, it.returnType);
        //前面classDef处理时已经把name改变过了
        currentFunc = new IRFunction(it.funcName, getIRType(it.returnType));
        currentBlock = currentFunc.entry;
        if (currentClass != null) addPara("this", new IRRegister(currentClass.asPtr(), "this"));
        if (it.params != null) it.params.accept(this);
        it.stmts.forEach(stmt -> stmt.accept(this));
        if (!currentFunc.isReturned) currentBlock.addInst(new retInst(currentBlock, BuiltinElements.irVoidConst));
        root.funcList.add(currentFunc);
        currentScope = currentScope.parentScope;
        currentFunc = null;
        currentBlock = null;
    }

    @Override
    public void visit(ParameterListNode it) {
        for (int i = 0; i < it.varList.size(); ++i) {
            VaraDefUnitNode vara = it.varList.get(i);
            addPara(vara.varName, new IRRegister(getIRType(vara.type), currentFunc.getRegId()));
        }
    }

    @Override
    public void visit(ClassBuildNode it) {
        currentScope = new Scope(currentScope, VoidType);
        String name = currentClass.name + "." + it.name;
        currentFunc = new IRFunction(name, BuiltinElements.irVoidType);
        currentBlock = currentFunc.entry;
        addPara("this", new IRRegister(currentClass.asPtr(), "this"));
        it.suites.stmts.forEach(stmt -> stmt.accept(this));
        currentBlock = null;
        currentFunc = null;
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ClassDefNode it) {
        currentScope = new Scope(currentScope, it);
        currentClass = new IRClassType(it.name, it.varMem.size() << 2);
        for (int i = 0; i < it.varMem.size(); ++i) {
            IRType varType = getIRType(it.varMem.get(i).type);
            currentClass.memberType.add(varType);
            currentClass.memberOffset.put(it.varMem.get(i).varName, i);
        }
        if (it.classBuilder != null) {
            it.classBuilder.accept(this);
        }
        for (FuncDefNode func : it.funcList) {
            func.funcName = it.name + "." + func.funcName;
            func.accept(this);
        }
        root.structTypeList.add(currentClass);
        structTypeMap.put(it.name,currentClass);
        currentClass = null;
        currentScope = currentScope.parentScope;
    }

    //============================================Stmt====================================================

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
    }


    @Override
    public void visit(IfStmtNode it) {
        it.judge.accept(this);
        IREntity condition = it.judge.entity;

        if (it.falseCon == null || it.falseCon.size() == 0) {
            IRBasicBlock thenBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock endBlock = new IRBasicBlock(currentFunc);
            currentBlock.addInst(new brInst(currentBlock, condition, thenBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = thenBlock;
            currentScope = new Scope(currentScope);
            it.trueCon.forEach(stmt -> stmt.accept(this));
            currentScope = currentScope.parentScope;
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = endBlock;
        } else {
            IRBasicBlock thenBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock elseBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock endBlock = new IRBasicBlock(currentFunc);

            currentBlock.addInst(new brInst(currentBlock, condition, thenBlock, elseBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = thenBlock;
            currentScope = new Scope(currentScope);
            it.trueCon.forEach(stmt -> stmt.accept(this));
            currentScope = currentScope.parentScope;
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));

            currentBlock = elseBlock;
            currentScope = new Scope(currentScope);
            it.falseCon.forEach(stmt -> stmt.accept(this));
            currentScope = currentScope.parentScope;
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = endBlock;
        }
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (it.returnExpr != null) {
            it.returnExpr.accept(this);
            currentBlock.addInst(new retInst(currentBlock, it.returnExpr.entity));
        } else currentBlock.addInst(new retInst(currentBlock));
        currentFunc.blocks.add(currentBlock);
        currentFunc.isReturned = true;
    }

    @Override
    public void visit(BreakStmtNode it) {
        currentBlock.addInst(new jumpInst(currentBlock, curEndBlock));
        //todo: maybe we need to create a new block??
    }

    @Override
    public void visit(ContinueStmtNode it) {
        currentBlock.addInst(new jumpInst(currentBlock, curBeginBlock));
    }

    @Override
    public void visit(ForStmtNode it) {
        currentScope = new Scope(currentScope);
        if (it.varDef != null) it.varDef.accept(this);
        else it.init.accept(this);

        IRBasicBlock tmpBeginBlock = curBeginBlock;
        IRBasicBlock tmpEndBlock = curEndBlock;

        IRBasicBlock conditionBlock = new IRBasicBlock(currentFunc);
        IRBasicBlock bodyBlock = new IRBasicBlock(currentFunc);
        curBeginBlock = new IRBasicBlock(currentFunc);
        curEndBlock = new IRBasicBlock(currentFunc);

        currentBlock.addInst(new jumpInst(currentBlock, conditionBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = conditionBlock;
        it.condition.accept(this);
        IREntity cond = it.condition.entity;
        currentBlock.addInst(new brInst(currentBlock, cond, bodyBlock, curEndBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = bodyBlock;
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentBlock.addInst(new jumpInst(currentBlock, curBeginBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = curBeginBlock;
        if (it.step != null) it.step.accept(this);
        currentBlock.addInst(new jumpInst(currentBlock, conditionBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = curEndBlock;

        curBeginBlock = tmpBeginBlock;
        curEndBlock = tmpEndBlock;
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(WhileStmtNode it) {
        IRBasicBlock tmpBeginBlock = curBeginBlock;
        IRBasicBlock tmpEndBlock = curEndBlock;

        curBeginBlock = new IRBasicBlock(currentFunc);
        IRBasicBlock bodyBlock = new IRBasicBlock(currentFunc);
        curEndBlock = new IRBasicBlock(currentFunc);

        currentBlock.addInst(new jumpInst(currentBlock, curBeginBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = curBeginBlock;
        it.condition.accept(this);
        IREntity cond = it.condition.entity;
        currentBlock.addInst(new brInst(currentBlock, cond, bodyBlock, curEndBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = bodyBlock;
        currentScope = new Scope(currentScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
        currentBlock.addInst(new jumpInst(currentBlock, curBeginBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = curEndBlock;

        curBeginBlock = tmpBeginBlock;
        curEndBlock = tmpEndBlock;
    }

    @Override
    public void visit(SuiteNode it) {
        currentScope = new Scope(currentScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(DefStmtNode it) {
        //可以是int a,也可以是A a;
        for (int i = 0; i < it.def.units.size(); ++i) {
            var unit = it.def.units.get(i);
//            String regName = unit.varName;
//            if (currentFunc != null) regName = currentFunc.name + regName;
//            else if (currentClass != null) regName = currentClass.name + regName;
            IRRegister reg = new IRRegister(getIRType(unit.type), currentFunc.getRegId());
            currentBlock.addInst(new allocateInst(currentBlock, reg, reg.type));
            currentScope.addVar(unit.varName, reg);
        }
    }

    //============================================Expr=================================================
    @Override
    public void visit(UnaryExprNode it) {
        it.expr.accept(this);
        IREntity ptr = it.expr.entity;
        IRRegister res = new IRRegister(ptr.type, currentFunc.getRegId());
        IRInst ins;
        ins = switch (it.op) {
            case "++" -> new binaryInst(currentBlock, "+", res, ptr, BuiltinElements.irIntConst1);
            case "--" -> new binaryInst(currentBlock, "-", res, ptr, BuiltinElements.irIntConst1);
            case "-" -> new binaryInst(currentBlock, "-", res, BuiltinElements.irIntConst0, ptr);
            case "~" -> new binaryInst(currentBlock, "^", res, ptr, BuiltinElements.irIntConstn1);
            case "!" -> new binaryInst(currentBlock, "^", res, ptr, BuiltinElements.irBoolTrue);
            default -> throw new irError("it should not exist", it.pos);
        };
        currentBlock.addInst(ins);
        it.entity = res;
    }

    @Override
    public void visit(PreAddExprNode it) {
        it.expr.accept(this);
        IREntity ptr = it.expr.entity;
        IRRegister res = new IRRegister(ptr.type, currentFunc.getRegId());
        IRInst ins;
        ins = switch (it.op) {
            case "++" -> new binaryInst(currentBlock, "+", res, ptr, BuiltinElements.irIntConst1);
            case "--" -> new binaryInst(currentBlock, "-", res, ptr, BuiltinElements.irIntConst1);
            default -> throw new irError("it should not exist", it.pos);
        };
        currentBlock.addInst(ins);
        it.entity = res;
    }


    @Override
    public void visit(BinaryExprNode it) {
        if (it.isLogic) {
            IRBasicBlock thenBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock trueBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock falseBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock endBlock = new IRBasicBlock(currentFunc);

            IRRegister res = new IRRegister(BuiltinElements.irBoolType, currentFunc.getRegId());
            currentBlock.addInst(new allocateInst(currentBlock, res, BuiltinElements.irBoolType));

            it.lhs.accept(this);
            IREntity lhs = it.lhs.entity;

            if (it.op.equals("&&")) currentBlock.addInst(new brInst(currentBlock, lhs, thenBlock, falseBlock));
            else if (it.op.equals("||")) currentBlock.addInst(new brInst(currentBlock, lhs, trueBlock, thenBlock));
            else throw new irError("it should not exist", it.pos);
            currentFunc.blocks.add(currentBlock);

            currentBlock = thenBlock;
            it.rhs.accept(this);
            IREntity rhs = it.rhs.entity;
            currentBlock.addInst(new brInst(currentBlock, rhs, trueBlock, falseBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = trueBlock;
            currentBlock.addInst(new storeInst(currentBlock, BuiltinElements.irBoolTrue, res));
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = falseBlock;
            currentBlock.addInst(new storeInst(currentBlock, BuiltinElements.irBoolFalse, res));
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = endBlock;
            it.entity = res;
            return;
        }

        it.lhs.accept(this);
        IREntity lhs = it.lhs.entity;
        it.rhs.accept(this);
        IREntity rhs = it.rhs.entity;
        IRRegister reg = new IRRegister(lhs.type, currentFunc.getRegId());
        if (it.isCmp) currentBlock.addInst(new lcmpInst(currentBlock, reg, it.op, lhs, rhs));
        else if (it.isAdd && lhs.isStr) {
            //todo:when it comes to string,we should use the 内置函数
        } else currentBlock.addInst(new binaryInst(currentBlock, it.op, reg, lhs, rhs));
        it.entity = reg;
    }

    @Override
    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);

        IREntity lhs = it.lhs.entity;
        IREntity rhs = it.rhs.entity;

        currentBlock.addInst(new storeInst(currentBlock, lhs, rhs));
    }


    @Override
    public void visit(TernaryExprNode it) {
        it.judge.accept(this);
        IREntity cond = it.judge.entity;
        it.trueCond.accept(this);
        it.falseCon.accept(this);
        IREntity res = new IRRegister(getIRType(it.type), currentFunc.getRegId());
        currentBlock.addInst(new selectInst(currentBlock, res, cond, it.trueCond.entity, it.falseCon.entity));
        it.entity = res;
    }

    @Override
    public void visit(BlockExprNode it) {
        it.exprs.forEach(expr->expr.accept(this));
    }

    @Override
    public void visit(AtomExprNode it) {
        //例如：a=1中的1 A=null中的null
        if(it.isConst){
            if(it.type.equals(IntType)) it.entity=new IRConst(irIntType,Integer.parseInt(it.str));
            else if(it.type.equals(BoolType)) it.entity=new IRConst(irBoolType,it.str.equals("true"));
            else if(it.type.equals(NullType)) it.entity=new IRConst(irNullType);
            else if(it.str.equals("this")) it.entity=new IRRegister(new IRPtrType(currentClass),currentClass.name);
            else if(it.type.equals(StringType)) {
                //todo:string
            }
            else throw new irError("it should not exist",it.pos);
        }
        //A.a中的A
        //全局寻找classType，然后返回IREntity type为转向classType的ptr
        else if(it.type.isClass){
            IRClassType classType=structTypeMap.get(it.str);
            it.entity=new IRRegister(new IRPtrType(classType),it.str);
        }
        //在function中的变量 从当前scope依次向上寻找，返回entity
        else {it.entity=currentScope.getIRVar(it.str);}
    }


    @Override
    public void visit(FuncExprNode it) {
        String name ;
        IRType fType ;
        ArrayList<IREntity> para = new ArrayList<>();
        if (it.funcName instanceof AtomExprNode) {
            if (currentClass != null) {
                IREntity classPtr = currentFunc.params.get(0);
                para.add(classPtr);
            }
            fType = getIRType(it.type);
            name = it.funcName.str;
        } else if (it.funcName instanceof MemExprNode) {
            ((MemExprNode) it.funcName).className.accept(this);
            IREntity callerEntity = ((MemExprNode) it.funcName).className.entity;
            assert callerEntity.type instanceof IRPtrType;
            IRType cType = ((IRPtrType) callerEntity.type).baseType;
            name = ((MemExprNode) it.funcName).member;

            if (cType instanceof IRClassType) {           //class
                String cName = ((MemExprNode) it.funcName).className.type.typeName;
                fType = getIRType(globalScope.classMems.get(cName).funcMem.get(name).returnType);
                name = cName + "." + name;
                para.add(callerEntity);
            } else if (cType instanceof IRIntType && ((IRIntType) cType).bitWidth == 8) {   //string
                name = "_string_" + name;
                fType = getIRType(globalScope.classMems.get("string").funcMem.get(name).returnType);
                para.add(callerEntity);
            } else {      //array
                name = "_array_size";
                fType = getIRType(globalScope.classMems.get("int").funcMem.get(name).returnType);
                para.add(callerEntity);
            }
        } else throw new irError("[function expression]", it.pos);
        for (int i = 0; i < it.lists.exprs.size(); ++i) {
            ExprNode e = it.lists.exprs.get(i);
            e.accept(this);
            IREntity expr = e.entity;
            para.add(expr);
        }
        IRRegister ret;
        if (fType.equals(irVoidType)) {
            ret = null;
            currentBlock.addInst(new callInst(currentBlock, name,para));
        } else {
            ret = new IRRegister(fType, currentFunc.getRegId());
            currentBlock.addInst(new callInst(currentBlock, fType, ret, name, para));
        }
        it.entity = ret;
    }

    @Override
    public void visit(MemExprNode it) {
        //func condition is already considered in FuncExprNode
        //here we only consider the [var in class]->class.val condition!!!
        it.className.accept(this);
        IREntity classPtr = it.className.entity;

        assert classPtr.type instanceof IRPtrType;
        assert ((IRPtrType) classPtr.type).baseType instanceof IRClassType;
        IRClassType cType= (IRClassType) ((IRPtrType) classPtr.type).baseType;

        int offset=cType.memberOffset.get(it.member);
        IRType type=cType.memberType.get(offset);
        IRRegister res=new IRRegister(type, currentFunc.getRegId());
        currentBlock.addInst(new getelementptrInstr(currentBlock,res,classPtr,BuiltinElements.irIntConst0,new IRConst(irIntType,offset)));
        it.entity=res;
    }

    @Override
    public void visit(NewExprNode it) {

    }

    @Override
    public void visit(ArrayExprNode it) {

    }


}