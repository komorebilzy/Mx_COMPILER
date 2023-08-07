package Util;

public class Type {
    //    public boolean isInt = false, isBool = false;
//    public HashMap<String, Type> members = null;
    public String typeName;
    public int dim = 0;
    public boolean isClass = false;
    public boolean isArray=false;
    public boolean isConst=false;

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
        this.isArray=other.isArray;
        this.isClass=other.isClass;
        this.isConst=other.isConst;
    }

    public Type(String typeName, int dim) {
        this(typeName);
        this.dim = dim;
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
