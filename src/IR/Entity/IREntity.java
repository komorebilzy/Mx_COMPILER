package IR.Entity;

import IR.Type.IRType;
import Util.BuiltinElements;

public abstract class IREntity implements BuiltinElements {
    public IRType type;

    IREntity(IRType type){
        this.type=type;
    }

    public abstract String getValue();

    public  String toString(){
        return type.toString();
    }

}
