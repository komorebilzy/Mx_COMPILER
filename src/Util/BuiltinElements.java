package Util;

import AST.*;
import AST.Def.FuncDefNode;
import AST.Def.ParameterListNode;
import AST.Def.VaraDefUnitNode;
import IR.Entity.IRConst;
import IR.Type.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface BuiltinElements {
    Type VoidType = new Type("void");
    Type IntType = new Type("int");
    Type BoolType = new Type("bool");
    Type StringType = new Type("string");
    Type NullType = new Type("null");
    Type ThisType = new Type("this");

    //内建函数
    FuncDefNode PrintFunc = new FuncDefNode(null, "print", VoidType, new ParameterListNode(null, new VaraDefUnitNode(null, StringType, "str")));
    FuncDefNode Println = new FuncDefNode(null, "println", VoidType, new ParameterListNode(null, new VaraDefUnitNode(null, StringType, "str")));
    FuncDefNode PrintInt = new FuncDefNode(null, "printInt", VoidType, new ParameterListNode(null, new VaraDefUnitNode(null, IntType, "n")));
    FuncDefNode PrintlnInt = new FuncDefNode(null, "printlnInt", VoidType, new ParameterListNode(null, new VaraDefUnitNode(null, IntType, "n")));
    FuncDefNode GetString = new FuncDefNode(null, "getString", StringType, new ParameterListNode(null));
    FuncDefNode GetInt = new FuncDefNode(null, "getInt", IntType, new ParameterListNode(null));
    FuncDefNode ToString = new FuncDefNode(null, "toString", StringType, new ParameterListNode(null, new VaraDefUnitNode(null, IntType, "i")));

    //字符串内建方法
    FuncDefNode Length = new FuncDefNode(null, "length", IntType, new ParameterListNode(null));
    FuncDefNode SubString = new FuncDefNode(null, "substring", StringType, new ParameterListNode(null, new VaraDefUnitNode(null, IntType, "left"), new VaraDefUnitNode(null, IntType, "right")));
    FuncDefNode ParseInt = new FuncDefNode(null, "parseInt", IntType, new ParameterListNode(null));
    FuncDefNode Ord = new FuncDefNode(null, "ord", IntType, new ParameterListNode(null, new VaraDefUnitNode(null, IntType, "pos")));

    //数组内建方法
    FuncDefNode Size = new FuncDefNode(null, "size", IntType, new ParameterListNode(null));


    //================================IR Builtin Types=========================================
    IRType irVoidType = new IRVoidType();
    IRType irIntType = new IRIntType(32);
    IRType irIntPtrType = new IRPtrType(irIntType);
    IRType irNullType = new IRPtrType(irVoidType);
    IRType irCharType = new IRIntType(8);
    IRType irBoolType = new IRIntType(1);
    IRType irStringType = new IRPtrType(irCharType);

//=================================IR Builtin Constants======================================

    IRConst irVoidConst = new IRConst(irVoidType, IRConst.constType.VOID);

    IRConst irBoolTrue = new IRConst(irBoolType, true);
    IRConst irBoolFalse = new IRConst(irBoolType, false);

    IRConst irIntConst0 = new IRConst(irIntType, 0);
    IRConst irIntConst1 = new IRConst(irIntType, 1);
    IRConst irIntConstn1 = new IRConst(irIntType, -1);
    IRConst irIntConst4 = new IRConst(irIntType, 4);

}
