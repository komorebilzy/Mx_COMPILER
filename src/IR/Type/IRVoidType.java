package IR.Type;

import IR.Entity.IREntity;
import Util.BuiltinElements;

public class IRVoidType extends IRType{
    public IRVoidType(){
        super("void",0);
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public IREntity defaultValue() {
        return BuiltinElements.irVoidConst;
    }
}
