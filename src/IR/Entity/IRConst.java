package IR.Entity;

import IR.Type.IRClassType;
import IR.Type.*;

public class IRConst extends IREntity {
    public enum constType {
        BOOL, INT, STRING, NULL, VOID
    }

    constType cType;
    public boolean i1;
    public int i32;
    public String str;

    public IRConst(IRType type) {
        super(type);
        if (type instanceof IRArrayType || type instanceof IRClassType || type instanceof IRPtrType)
            cType = constType.NULL;
        else if (type instanceof IRIntType) {
            if (((IRIntType) type).bitWidth == 1) cType = constType.BOOL;
            else cType = constType.INT;
        } else if (type instanceof IRVoidType) cType = constType.VOID;
        i1 = false;
        i32 = 0;
        str = "";
    }

    public IRConst(IRType type,int i32){
        super(type);
        cType=constType.INT;
        this.i32=i32;
    }

    public IRConst(IRType type,boolean i1){
        super(type);
        cType=constType.BOOL;
        this.i1=i1;
    }

    public IRConst(IRType type,String str){
        super(type);
        cType=constType.STRING;
        this.str=str;
    }

    public IRConst(IRType type,constType cType){
        super(type);
        this.cType=cType;
    }

    @Override
    public String getValue() {
        return switch (cType){
            case BOOL -> Boolean.toString(i1);
            case INT -> Integer.toString(i32);
            case STRING -> "c\""+str+"\\0\"";
            case NULL -> "null";
            case VOID -> "";
        };
    }

    @Override
    public String toString() {
        return super.toString()+" "+getValue();
    }

    public boolean isZero(){
        return switch (cType){
            case BOOL -> !i1;
            case INT -> i32==0;
            case STRING, VOID -> false;
            case NULL -> true;
        };
    }

    public boolean equals(IRConst obj){
        if(cType!=obj.cType) return false;
        if(cType==constType.BOOL && i1!=obj.i1) return false;
        if(cType==constType.INT && i32!=obj.i32) return false;
        if(cType==constType.STRING && !str.equals(obj.str)) return false;
        return true;
    }
}
