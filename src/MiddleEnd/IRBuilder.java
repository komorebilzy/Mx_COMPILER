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

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor, BuiltinElements {
    IRFunction currentFunc = null;
    IRClassType currentClass = null;
    IRBasicBlock currentBlock = null;
    GlobalScope globalScope;
    Scope currentScope = null;
    IRProgram root;
    ArrayList<callInst> vara_init = new ArrayList<>();

    //used for while and for statement
    IRBasicBlock curBeginBlock = null;
    IRBasicBlock curEndBlock = null;

    HashMap<String, IRClassType> structTypeMap = new HashMap<>();

    public IRBuilder(IRProgram root, GlobalScope globalScope) {
        this.root = root;
        this.globalScope = globalScope;
        currentScope = globalScope;
        addDeclare();
    }

    private void addDeclare() {
        currentBlock = new IRBasicBlock("init");
        declareInst Print = new declareInst(currentBlock, irVoidType, "print", new IRRegister(irStringType, "s"));
        declareInst PrintLn = new declareInst(currentBlock, irVoidType, "println", new IRRegister(irStringType, "s"));
        declareInst PrintInt = new declareInst(currentBlock, irVoidType, "printInt", new IRRegister(irIntType, "x"));
        declareInst PrintlnInt = new declareInst(currentBlock, irVoidType, "printlnInt", new IRRegister(irIntType, "x"));
        declareInst GetString = new declareInst(currentBlock, irStringType, "getString");
        declareInst GetInt = new declareInst(currentBlock, irIntType, "getInt");
        declareInst ToString = new declareInst(currentBlock, irStringType, "toString", new IRRegister(irIntType, "x"));

        declareInst Length = new declareInst(currentBlock, irIntType, "__string.length", new IRRegister(irStringType, "s"));
        declareInst SubString = new declareInst(currentBlock, irStringType, "__string.substring", new IRRegister(irStringType, "s"), new IRRegister(irIntType, "l"), new IRRegister(irIntType, "r"));
        declareInst ParseInt = new declareInst(currentBlock, irIntType, "__string.parseInt", new IRRegister(irStringType, "s"));
        declareInst Ord = new declareInst(currentBlock, irIntType, "__string.ord", new IRRegister(irStringType, "s"), new IRRegister(irIntType, "x"));
        declareInst Add = new declareInst(currentBlock, irStringType, "__string.add", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Slt = new declareInst(currentBlock, irBoolType, "__string.slt", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Sle = new declareInst(currentBlock, irBoolType, "__string.sle", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Sgt = new declareInst(currentBlock, irBoolType, "__string.sgt", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Sge = new declareInst(currentBlock, irBoolType, "__string.sge", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Eq = new declareInst(currentBlock, irBoolType, "__string.eq", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Ne = new declareInst(currentBlock, irBoolType, "__string.ne", new IRRegister(irStringType, "s"), new IRRegister(irStringType, "t"));
        declareInst Malloc = new declareInst(currentBlock, irStringType, "_malloc", new IRRegister(irIntType, "size"));

        root.DeclareList.add(Print);
        root.DeclareList.add(PrintLn);
        root.DeclareList.add(PrintInt);
        root.DeclareList.add(PrintlnInt);
        root.DeclareList.add(GetString);
        root.DeclareList.add(GetInt);
        root.DeclareList.add(ToString);
        root.DeclareList.add(Length);
        root.DeclareList.add(SubString);
        root.DeclareList.add(ParseInt);
        root.DeclareList.add(Ord);
        root.DeclareList.add(Add);
        root.DeclareList.add(Sge);
        root.DeclareList.add(Slt);
        root.DeclareList.add(Sle);
        root.DeclareList.add(Sgt);
        root.DeclareList.add(Eq);
        root.DeclareList.add(Ne);
        root.DeclareList.add(Malloc);

        currentBlock = null;

    }

    IRType getIRType(Type type) {
        IRType irType;
        switch (type.typeName) {
            case "int":
                irType = irIntType;
                if (type.dim >= 1) irType = new IRPtrType(irType, type.dim);
                break;
            case "bool":
                irType = irBoolType;
                if (type.dim >= 1) irType = new IRPtrType(irType, type.dim);
                break;
            case "string":
                irType = irStringType;
                if (type.dim >= 1) irType = new IRPtrType(irType, type.dim);
                break;
            case "void":
                irType = irVoidType;
                break;
            default:
                //debug: class A{int a,A M}
                irType = new IRPtrType(structTypeMap.get(type.typeName), 1);
        }
        return irType;
    }

    //=================================================Definition=======================================================
    @Override
    public void visit(RootNode it) {
        //collect StructType
        for (int i = 0; i < it.DefList.size(); ++i) {
            if (it.DefList.get(i) instanceof ClassDefNode) {
                ClassDefNode def = (ClassDefNode) it.DefList.get(i);
                IRClassType classType = new IRClassType(def.name, def.varMem.size() << 2);
                structTypeMap.put(classType.name, classType);
            }
        }
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
            IRFunction globalInit = IRFunction.globalVarInit(it.varName);
            currentFunc = globalInit;
            currentBlock = currentFunc.entry;
            if (it.init != null) {

                it.init.accept(this);
            }
            //全局定义数组 int [][]a=new int [][];   在getIRType中已经处理了这种情况
            IRGlobalVal gVal = new IRGlobalVal(getIRType(it.type), it.varName);
            //int a=1; int b=a;
            if (it.init instanceof AtomExprNode) {
                var entity = it.init.entity;
                if (((AtomExprNode) it.init).isConst) {
                    gVal.init = entity;
                } else {
                    gVal.init = entity.type.defaultValue();
                    IRRegister tmp = new IRRegister(gVal.type, globalInit.getRegId());
                    globalInit.entry.insts.add(0, new loadInst(null, tmp, entity));
                    globalInit.entry.insts.add(1, new storeInst(null, gVal, tmp));
                    globalInit.entry.insts.add(2, new retInst(null, BuiltinElements.irVoidConst));
                    globalInit.blocks.add(globalInit.entry);
                    root.funcList.add(globalInit);
                    vara_init.add(new callInst(globalInit.entry, globalInit.name, null));
                }
            }
            //int m ; A m;
            else if (it.init == null) {
                gVal.init = gVal.type.defaultValue();
            } else {
                //int [][]a
                gVal.init = it.init.entity.type.defaultValue();
                currentBlock.insts.add(new storeInst(null, gVal, it.init.entity));
                currentBlock.insts.add(new retInst(null, BuiltinElements.irVoidConst));
                currentFunc.blocks.add(currentBlock);
                root.funcList.add(currentFunc);
                vara_init.add(new callInst(globalInit.entry, globalInit.name, null));
            }
            currentBlock = null;
            currentFunc = null;
            root.globalVarList.add(gVal);
            globalScope.addVar(it.varName, gVal);
        }
        //register
        else {
            //A a=new A(); int a=1;
            IRRegister register = new IRRegister(getIRType(it.type), currentFunc.getRegId());
            //class自定义的vara变量已经处理了 不需要考虑
            currentBlock.addInst(new allocateInst(currentBlock, register, register.type));
            if (it.init != null) {
                it.init.accept(this);
                IREntity value = it.init.entity;
                currentBlock.addInst(new storeInst(currentBlock, register, value));
                if (it.type.isClass && it.init.be_built) {
                    IRRegister buildRes = new IRRegister(register.type, currentFunc.getRegId());
                    currentBlock.addInst(new loadInst(currentBlock, buildRes, register));
                    ArrayList<IREntity> paras = new ArrayList<>();
                    paras.add(buildRes);
                    ((callInst) it.init.built_func).args = paras;
                    currentBlock.addInst(it.init.built_func);
                }
            }
            currentScope.addVar(it.varName, register);
        }
    }

    private void addPara(String name, IRRegister reg) {
        currentFunc.params.add(reg);
        IRRegister addr = new IRRegister(reg.type, currentFunc.getRegId());
        currentBlock.addInst(new allocateInst(currentBlock, addr, reg.type));
        currentBlock.addInst(new storeInst(currentBlock, addr, reg));
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
        if (it.isMain) vara_init.forEach(vara -> currentBlock.addInst(vara));
        it.stmts.forEach(stmt -> stmt.accept(this));
        if (!currentFunc.isReturned || !currentBlock.isFinished) {
            currentBlock.addInst(new retInst(currentBlock, currentFunc.returnType.defaultValue()));
        }
        if (currentBlock != null && currentBlock.insts != null && currentBlock.insts.size() != 0)
            currentFunc.blocks.add(currentBlock);
        root.funcList.add(currentFunc);
        currentScope = currentScope.parentScope;
        currentFunc = null;
        currentBlock = null;
    }

    @Override
    public void visit(ParameterListNode it) {
        for (int i = 0; i < it.varList.size(); ++i) {
            VaraDefUnitNode vara = it.varList.get(i);
            addPara(vara.varName, new IRRegister(getIRType(vara.type), vara.varName));
        }
    }

    @Override
    public void visit(ClassBuildNode it) {
        currentScope = new Scope(currentScope, VoidType);
        String name = currentClass.name + ".built_function";
        currentFunc = new IRFunction(name, BuiltinElements.irVoidType);
        currentBlock = currentFunc.entry;
        addPara("this", new IRRegister(currentClass.asPtr(), "this"));
        it.suites.stmts.forEach(stmt -> stmt.accept(this));
        currentBlock.addInst(new retInst(currentBlock, BuiltinElements.irVoidConst));
        currentFunc.blocks.add(currentBlock);
        root.funcList.add(currentFunc);
        currentBlock = null;
        currentFunc = null;
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ClassDefNode it) {
        currentScope = new Scope(currentScope, it);
        currentClass = structTypeMap.get(it.name);
        int size = 0;
        int num = -1;
        for (int i = 0; i < it.varList.size(); ++i) {
            var units = it.varList.get(i).units;
            for (int j = 0; j < units.size(); ++j) {
                IRType varType = getIRType(units.get(j).type);
                size += varType.size;
                currentClass.memberType.add(varType);
                currentClass.memberOffset.put(units.get(j).varName, ++num);
            }
        }
        currentClass.size = size;
        if (it.classBuilder != null) {
            currentClass.isBuilt = true;
            it.classBuilder.accept(this);
        }
        for (FuncDefNode func : it.funcList) {
            func.funcName = it.name + "." + func.funcName;
            func.accept(this);
        }
        root.structTypeList.add(currentClass);
        currentClass = null;
        currentScope = currentScope.parentScope;
    }

    //============================================Stmt====================================================

    @Override
    public void visit(ExprStmtNode it) {
        if (it.expr != null) it.expr.accept(this);
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
            currentFunc.blocks.add(currentBlock);

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
//        currentFunc.blocks.add(currentBlock);
        currentFunc.isReturned = true;
    }

    @Override
    public void visit(BreakStmtNode it) {
        currentBlock.addInst(new jumpInst(currentBlock, curEndBlock));
    }

    @Override
    public void visit(ContinueStmtNode it) {
        currentBlock.addInst(new jumpInst(currentBlock, curBeginBlock));
    }

    @Override
    public void visit(ForStmtNode it) {
        currentScope = new Scope(currentScope);
        if (it.varDef != null) it.varDef.accept(this);
        else if (it.init != null) it.init.accept(this);

        IRBasicBlock tmpBeginBlock = curBeginBlock;
        IRBasicBlock tmpEndBlock = curEndBlock;

        IRBasicBlock conditionBlock = new IRBasicBlock(currentFunc);
        IRBasicBlock bodyBlock = new IRBasicBlock(currentFunc);
        curBeginBlock = new IRBasicBlock(currentFunc);
        curEndBlock = new IRBasicBlock(currentFunc);

        currentBlock.addInst(new jumpInst(currentBlock, conditionBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = conditionBlock;
        IREntity cond = irBoolTrue;
        if (it.condition != null) {
            it.condition.accept(this);
            cond = it.condition.entity;
        }
        currentBlock.addInst(new brInst(currentBlock, cond, bodyBlock, curEndBlock));
        currentFunc.blocks.add(currentBlock);

        //必须先++i再遍历body  防止出现情况：for(i=2;;++i){int i=2;}
        currentBlock = curBeginBlock;
        if (it.step != null) it.step.accept(this);
        currentBlock.addInst(new jumpInst(currentBlock, conditionBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = bodyBlock;
        if (it.stmts.size() != 0) it.stmts.forEach(stmt -> stmt.accept(this));
        currentBlock.addInst(new jumpInst(currentBlock, curBeginBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = curEndBlock;

        curBeginBlock = tmpBeginBlock;
        curEndBlock = tmpEndBlock;
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(WhileStmtNode it) {
        currentScope = new Scope(currentScope);
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
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(SuiteNode it) {
        currentScope = new Scope(currentScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(DefStmtNode it) {
        //可以是int a,也可以是A a;    还可以是int a=1;!!!
//        for (int i = 0; i < it.def.units.size(); ++i) {
//            var unit = it.def.units.get(i);
//            IRRegister reg = new IRRegister(getIRType(unit.type), currentFunc.getRegId());
//            currentBlock.addInst(new allocateInst(currentBlock, reg, reg.type));
//            currentScope.addVar(unit.varName, reg);
//        }
        it.def.units.forEach(unit -> unit.accept(this));
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
        if (it.op.equals("++") || it.op.equals("--")) {
            it.expr.be_assigned = true;
            it.expr.accept(this);
            IREntity primary = it.expr.entity;
            currentBlock.addInst(new storeInst(currentBlock, primary, res));
            it.entity = ptr;
        }

    }

    @Override
    public void visit(PreAddExprNode it) {
        it.expr.accept(this);
        IREntity ptr = it.expr.entity;
        it.expr.be_assigned = true;
        it.expr.accept(this);
        IREntity primary = it.expr.entity;
        IRRegister res = new IRRegister(ptr.type, currentFunc.getRegId());
        IRInst ins;
        ins = switch (it.op) {
            case "++" -> new binaryInst(currentBlock, "+", res, ptr, BuiltinElements.irIntConst1);
            case "--" -> new binaryInst(currentBlock, "-", res, ptr, BuiltinElements.irIntConst1);
            default -> throw new irError("it should not exist", it.pos);
        };
        currentBlock.addInst(ins);
        currentBlock.addInst(new storeInst(currentBlock, primary, res));
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
            currentBlock.addInst(new storeInst(currentBlock, res, BuiltinElements.irBoolTrue));
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = falseBlock;
            currentBlock.addInst(new storeInst(currentBlock, res, BuiltinElements.irBoolFalse));
            currentBlock.addInst(new jumpInst(currentBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = endBlock;
            IRRegister loadRes = new IRRegister(irBoolType, currentFunc.getRegId());
            currentBlock.addInst(new loadInst(currentBlock, loadRes, res));
            it.entity = loadRes;
            return;
        }

        it.lhs.accept(this);
        IREntity lhs = it.lhs.entity;
        it.rhs.accept(this);
        IREntity rhs = it.rhs.entity;
        //when it comes to string,we should call the 内置函数
        ArrayList<IREntity> paras = new ArrayList<>();
        paras.add(lhs);
        paras.add(rhs);
        if (it.isCmp && it.lhs.type.equals(StringType)) {
            IRRegister reg = new IRRegister(irBoolType, currentFunc.getRegId());
            String cond = "__string.";
            switch (it.op) {
                case "<" -> cond += "slt";
                case ">" -> cond += "sgt";
                case "<=" -> cond += "sle";
                case ">=" -> cond += "sge";
                case "==" -> cond += "eq";
                case "!=" -> cond += "ne";
            }
            currentBlock.addInst(new callInst(currentBlock, reg.type, reg, cond, paras));
            it.entity = reg;
        } else if (it.isCmp) {
            IRRegister reg = new IRRegister(irBoolType, currentFunc.getRegId());
            currentBlock.addInst(new lcmpInst(currentBlock, reg, it.op, lhs, rhs));
            it.entity = reg;
        } else if (it.isAdd && it.lhs.type.equals(StringType)) {
            IRRegister rest = new IRRegister(new IRPtrType(irCharType), currentFunc.getRegId());
            currentBlock.addInst(new callInst(currentBlock, rest.type, rest, "__string.add", paras));
            it.entity = rest;
        } else {
            IRRegister reg = new IRRegister(lhs.type, currentFunc.getRegId());
            currentBlock.addInst(new binaryInst(currentBlock, it.op, reg, lhs, rhs));
            it.entity = reg;
        }
    }

    @Override
    public void visit(AssignExprNode it) {
        it.lhs.be_assigned = true;
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
//        it.trueCond.accept(this);
//        it.falseCon.accept(this);
//        IREntity res = new IRRegister(getIRType(it.type), currentFunc.getRegId());
//        currentBlock.addInst(new selectInst(currentBlock, res, cond, it.trueCond.entity, it.falseCon.entity));
//        it.entity = res;
        IRBasicBlock thenBlock = new IRBasicBlock(currentFunc);
        IRBasicBlock elseBlock = new IRBasicBlock(currentFunc);
        IRBasicBlock endBlock = new IRBasicBlock(currentFunc);


        currentBlock.addInst(new brInst(currentBlock, cond, thenBlock, elseBlock));
        currentFunc.blocks.add(currentBlock);

        currentBlock = thenBlock;
        currentScope = new Scope(currentScope);
        it.trueCond.accept(this);
        currentScope = currentScope.parentScope;
        currentBlock.addInst(new jumpInst(currentBlock, endBlock));
        thenBlock=currentBlock;
        currentFunc.blocks.add(currentBlock);

        currentBlock = elseBlock;
        currentScope = new Scope(currentScope);
        it.falseCon.accept(this);
        currentScope = currentScope.parentScope;
        currentBlock.addInst(new jumpInst(currentBlock, endBlock));
        elseBlock=currentBlock;
        currentFunc.blocks.add(currentBlock);

        currentBlock = endBlock;
        if(it.trueCond.entity!=null){
            IRRegister res = new IRRegister(getIRType(it.type), currentFunc.getRegId());
            phiInst inst = new phiInst(currentBlock, res);
            inst.add(it.trueCond.entity, thenBlock);
            inst.add(it.falseCon.entity, elseBlock);
            currentBlock.addInst(inst);
            it.entity = res;
        }
        else it.entity=null;

    }

    @Override
    public void visit(BlockExprNode it) {
        it.exprs.forEach(expr -> expr.accept(this));
    }

    @Override
    public void visit(AtomExprNode it) {
        //例如：a=1中的1 A=null中的null
        if (it.isConst) {
            if (it.type.equals(IntType)) it.entity = new IRConst(irIntType, Integer.parseInt(it.str));
            else if (it.type.equals(BoolType)) it.entity = new IRConst(irBoolType, it.str.equals("true"));
            else if (it.type.equals(NullType)) it.entity = new IRConst(irNullType);
            else if (it.str.equals("this")) {
                IREntity resPtr = currentScope.getIRVar(it.str);
                IRRegister res = new IRRegister(resPtr.type, currentFunc.getRegId());
                currentBlock.addInst(new loadInst(currentBlock, res, resPtr));
                it.entity = res;
            } else if (it.type.equals(StringType)) {
                IREntity tmp = new IRConst(BuiltinElements.irStringType, it.str);
                IRGlobalVal stringGlobal = new IRGlobalVal(BuiltinElements.irStringType, "str." + (++root.strConstNum), tmp);
                stringGlobal.isString = true;
                root.globalVarList.add(stringGlobal);
                it.entity = stringGlobal;
            } else throw new irError("it should not exist", it.pos);
        }
        //A.a中的A
        //全局寻找classType，然后返回IREntity type为转向classType的ptr
        else if (it.type.isClass) {
            if (it.be_assigned) {
                it.entity = currentScope.getIRVar(it.str);
            } else {
                IRClassType classType = structTypeMap.get(it.type.typeName);
                IRRegister res = new IRRegister(new IRPtrType(classType), currentFunc.getRegId());
                currentBlock.addInst(new loadInst(currentBlock, res, currentScope.getIRVar(it.str)));
                it.entity = res;
            }
        }
        //在function中的变量 从当前scope依次向上寻找，返回entity
        //也有可能这里的vara是class中的变量
        else {
            IREntity tmp = currentScope.getIRVar(it.str);
            if (tmp == null) {
                IREntity classPtr = currentScope.getIRVar("this");

                assert classPtr.type instanceof IRPtrType;
                assert ((IRPtrType) classPtr.type).baseType instanceof IRClassType;
                IRClassType cType = (IRClassType) ((IRPtrType) classPtr.type).baseType;

                IRRegister loadClass = new IRRegister(classPtr.type, currentFunc.getRegId());
                currentBlock.addInst(new loadInst(currentBlock, loadClass, classPtr));

                tmp = new IRRegister(cType.getMemberType(it.str), currentFunc.getRegId());
                currentBlock.addInst(new getelementptrInstr(currentBlock, (IRRegister) tmp, loadClass, BuiltinElements.irIntConst0, new IRConst(irIntType, cType.memberOffset.get(it.str))));
                it.entity = tmp;

            } else if (currentScope == globalScope || it.be_assigned) {
                it.entity = tmp;
                return;
            }
            // 注意到这里左值和右值是有区别的
            //b=1时只需要一个store指令
            //a=b对于其中的b我们就要再load出来一个局部变量
            if (!it.be_assigned) {
                IRRegister res = new IRRegister(tmp.type, currentFunc.getRegId());
                currentBlock.addInst(new loadInst(currentBlock, res, tmp));
                it.entity = res;
            }

        }
    }


    @Override
    public void visit(FuncExprNode it) {
        String name;
        IRType fType;
        ArrayList<IREntity> para = new ArrayList<>();
        if (it.funcName instanceof AtomExprNode) {
            fType = getIRType(it.type);
            //在访问 类中函数定义node 时已经将名字改成对应的class+name****
            name = it.funcName.str;
            if (currentClass != null && globalScope.getClass(currentClass.name).funcMem.containsKey(name)) {
                IREntity classPtr = currentFunc.params.get(0);
                para.add(classPtr);
                name = currentClass.name + "." + name;
            }
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
                fType = getIRType(globalScope.classMems.get("string").funcMem.get(name).returnType);
                name = "__string." + name;
                para.add(callerEntity);
            } else {                //array
                IREntity sizePtr = offsetPtr(callerEntity, BuiltinElements.irIntConstn1);
                IRRegister ret = new IRRegister(irIntType, currentFunc.getRegId());
                currentBlock.addInst(new loadInst(currentBlock, ret, sizePtr));
                it.entity = ret;
                return;
            }
        } else throw new irError("[function expression]", it.pos);
        if (it.lists != null && it.lists.exprs.size() != 0) {
            for (int i = 0; i < it.lists.exprs.size(); ++i) {
                ExprNode e = it.lists.exprs.get(i);
                e.accept(this);
                IREntity expr = e.entity;
                para.add(expr);
            }
        }
        IRRegister ret;
        if (fType.equals(irVoidType)) {
            ret = null;
            currentBlock.addInst(new callInst(currentBlock, name, para));
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
        IRClassType cType = (IRClassType) ((IRPtrType) classPtr.type).baseType;

        int offset = cType.memberOffset.get(it.member);
        IRType type = cType.memberType.get(offset);
        IRRegister resPtr = new IRRegister(new IRPtrType(type), currentFunc.getRegId());

        currentBlock.addInst(new getelementptrInstr(currentBlock, resPtr, classPtr, BuiltinElements.irIntConst0, new IRConst(irIntType, offset)));
        if (it.be_assigned) {
            it.entity = resPtr;
            return;
        }
        IRRegister res = new IRRegister(type, currentFunc.getRegId());
        currentBlock.addInst(new loadInst(currentBlock, res, resPtr));
        it.entity = res;
    }

    private IREntity getArrayBytes(IRType ptr, IREntity size) {
        if (size instanceof IRConst) {
            //数组本身加上数组长度
            int bytes = ((IRConst) size).i32 * 4 + 4;
            return new IRConst(BuiltinElements.irIntType, bytes);
        }

        IRRegister tmp = new IRRegister(irIntType, currentFunc.getRegId());
        currentBlock.addInst(new binaryInst(currentBlock, "*", tmp, size, BuiltinElements.irIntConst4));

        IRRegister bytes = new IRRegister(BuiltinElements.irIntType, currentFunc.getRegId());
        currentBlock.addInst(new binaryInst(currentBlock, "+", bytes, tmp, BuiltinElements.irIntConst4));
        return bytes;
    }

    //返回一个指向ptr中offset位置的指针entity
    private IRRegister offsetPtr(IREntity ptr, IREntity offset) {
        IRType ptrType = ptr.type;
        IRRegister offsetPtr = new IRRegister(ptrType, currentFunc.getRegId());
        currentBlock.addInst(new getelementptrInstr(currentBlock, offsetPtr, ptr, offset));
        return offsetPtr;
    }

    private IREntity createNewArray(IRType ptrType, int layer, ArrayList<ExprNode> dimSizes) {
        if (dimSizes.get(layer) == null) return irNullConst;
        dimSizes.get(layer).accept(this);
        IREntity size = dimSizes.get(layer).entity;
        IREntity bytes = getArrayBytes(ptrType, size);

        //malloc
        IRRegister mallocPtr = new IRRegister(ptrType, currentFunc.getRegId());
        ArrayList<IREntity> para = new ArrayList<>();
        para.add(bytes);
        currentBlock.addInst(new callInst(currentBlock, mallocPtr.type, mallocPtr, "_malloc", para));

        //store size(length) at the beginning of the array
        currentBlock.addInst(new storeInst(currentBlock, mallocPtr, size));

        //move pointer to the offset 1
        IRRegister realArrayPtr = offsetPtr(mallocPtr, irIntConst1);

        if (layer < dimSizes.size() - 1) {
            //create array by while loop
            IRBasicBlock condBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock bodyBlock = new IRBasicBlock(currentFunc);
            IRBasicBlock endBlock = new IRBasicBlock(currentFunc);

            //int i=0;
            IRRegister IPtr = new IRRegister(BuiltinElements.irIntPtrType, currentFunc.getRegId());
            currentBlock.addInst(new allocateInst(currentBlock, IPtr, BuiltinElements.irIntType));
            currentBlock.addInst(new storeInst(currentBlock, IPtr, BuiltinElements.irIntConst0));
            currentBlock.addInst(new jumpInst(currentBlock, condBlock));
            currentFunc.blocks.add(currentBlock);

            //cond:i<size
            currentBlock = condBlock;
            IRRegister IVal = new IRRegister(BuiltinElements.irIntType, currentFunc.getRegId());
            currentBlock.addInst(new loadInst(currentBlock, IVal, IPtr));
            IRRegister cond = new IRRegister(irBoolType, currentFunc.getRegId());
            currentBlock.addInst(new lcmpInst(currentBlock, cond, "<", IVal, size));
            currentBlock.addInst(new brInst(currentBlock, cond, bodyBlock, endBlock));
            currentFunc.blocks.add(currentBlock);

            //body:create next layer array
            currentBlock = bodyBlock;
            IREntity nextLayerPtr = createNewArray(ptrType.pointToType(), layer + 1, dimSizes);
            IRRegister curPtr = offsetPtr(realArrayPtr, IVal);
            currentBlock.addInst(new storeInst(currentBlock, curPtr, nextLayerPtr));

            //++i
            IRRegister IValPlus = new IRRegister(BuiltinElements.irIntType, currentFunc.getRegId());
            currentBlock.addInst(new binaryInst(currentBlock, "+", IValPlus, IVal, BuiltinElements.irIntConst1));
            currentBlock.addInst(new storeInst(currentBlock, IPtr, IValPlus));
            currentBlock.addInst(new jumpInst(currentBlock, condBlock));
            currentFunc.blocks.add(currentBlock);

            currentBlock = endBlock;
        }
        return realArrayPtr;
    }

    @Override
    public void visit(NewExprNode it) {
        IRType type = getIRType(it.type);
        //class
        if (it.dim == 0) {
            IRClassType classType = (IRClassType) ((IRPtrType) type).baseType;
            IRRegister res = new IRRegister(new IRPtrType(irIntType), currentFunc.getRegId());
            ArrayList<IREntity> para = new ArrayList<>();
            para.add(new IRConst(BuiltinElements.irIntType, classType.size + 4));
            currentBlock.addInst(new callInst(currentBlock, res.type, res, "_malloc", para));
            it.entity = res;

            if (classType.isBuilt) {
                it.be_built = true;
                it.built_func = new callInst(currentBlock, classType.name + ".built_function", null);
            }
        }
        //array
        else {
            it.entity = createNewArray(type.asPtr(), 0, it.lists);
        }
    }

    @Override
    public void visit(ArrayExprNode it) {
        it.name.accept(this);
        IREntity arrDest = it.name.entity;
        IRRegister res = null;
        for (int i = 0; i < it.index.size(); ++i) {
            ExprNode index = it.index.get(i);
            index.accept(this);
            //对于classType 必须在外面再套一层ptr
            if(arrDest.type instanceof IRPtrType && ((IRPtrType) arrDest.type).baseType instanceof IRClassType)
                arrDest.type=arrDest.type.asPtr();

            arrDest = offsetPtr(arrDest, index.entity);
            if(i == it.index.size() - 1 && it.be_assigned){
                it.entity = arrDest;
                return;
            }
            res = new IRRegister(arrDest.type.pointToType(), currentFunc.getRegId());
            currentBlock.addInst(new loadInst(currentBlock, res, arrDest));
            arrDest = res;
        }
        it.entity = res;
    }
}