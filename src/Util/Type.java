package Util;

import AST.Def.FuncDefNode;

public class Type {
    //    public boolean isInt = false, isBool = false;
//    public HashMap<String, Type> members = null;
    public String typeName;
    public int dim = 0;
    public boolean isClass = false;
    public boolean isFunc=false;
    public FuncDefNode funcDef=null;

    public Type(String typeName) {
        this.typeName = typeName;
        if (!typeName.equals("void")
                && !typeName.equals("int")
                && !typeName.equals("bool")
                && !typeName.equals("string")
                && !typeName.equals("null")
                && !typeName.equals("this"))
            isClass = true;
    }
    public Type(Type other){
        this.typeName= other.typeName;
        this.dim= other.dim;
        this.isClass=other.isClass;
    }

    public Type(String typeName, int dim) {
        this(typeName);
        this.dim = dim;
    }

    public Type(FuncDefNode def){
        this.funcDef=def;
        this.typeName=def.funcName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Type))
            return false;
        Type type1 = (Type) obj;
        if (this.dim != type1.dim)
            return false;
        if (!this.typeName.equals(type1.typeName))
            return false;
        return true;
    }

}
