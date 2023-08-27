package IR.Entity;

import IR.Type.IRClassType;
import IR.Type.*;

public class IRConst extends IREntity {
    public enum constType {
        BOOL, INT, STRING, NULL, VOID
    }

    public  constType cType;
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

    public IRConst(IRType type, int i32) {
        super(type);
        cType = constType.INT;
        this.i32 = i32;
    }

    public IRConst(IRType type, boolean i1) {
        super(type);
        cType = constType.BOOL;
        this.i1 = i1;
    }

    public IRConst(IRType type, String str) {
        super(type);
        cType = constType.STRING;
        int len=str.length();
        this.str=str.substring(1,len-1);
    }

    public IRConst(IRType type, constType cType) {
        super(type);
        this.cType = cType;
    }

    //=============================specially for string==========================
    private String convertStr(String s) {
        StringBuilder conversion = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '\\') {
                char next = s.charAt(i + 1);
                if (next == 'n') {
                    conversion.append("\\0A");
                    ++i;
                } else if (next == '\\') {
                    conversion.append("\\\\");
                    ++i;
                } else if (next == '\"') {
                    conversion.append("\\22");
                    ++i;
                } else conversion.append(c);
            } else conversion.append(c);
        }
        return conversion.toString();
    }

    private int escCount(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '\\') {
                char next = s.charAt(i + 1);
                if (next == 'n' || next == '\\' || next == '\"') {
                    ++cnt;
                    ++i;
                }
            }
        }
        return cnt;
    }

    private int size() {
        return str.length() + 1 - escCount(str);
    }

    @Override
    public String getValue() {
        return switch (cType) {
            case BOOL -> Boolean.toString(i1);
            case INT -> Integer.toString(i32);
            case STRING -> "c\"" + convertStr(str) + "\\00\"";
            case NULL -> "null";
            case VOID -> "";
        };
    }

    @Override
    public String toString() {
        if (cType == constType.STRING) return "private unnamed_addr constant [" + size() + " x i8] " + getValue();
        return super.toString() + " " + getValue();
    }

    public boolean isZero() {
        return switch (cType) {
            case BOOL -> !i1;
            case INT -> i32 == 0;
            case STRING, VOID -> false;
            case NULL -> true;
        };
    }

    public boolean equals(IRConst obj) {
        if (cType != obj.cType) return false;
        if (cType == constType.BOOL && i1 != obj.i1) return false;
        if (cType == constType.INT && i32 != obj.i32) return false;
        if (cType == constType.STRING && !str.equals(obj.str)) return false;
        return true;
    }
}
