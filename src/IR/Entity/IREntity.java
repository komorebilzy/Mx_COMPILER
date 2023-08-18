package IR.Entity;

import IR.Type.IRType;
import Util.BuiltinElements;

public abstract class IREntity implements BuiltinElements {
    public IRType type;
    public boolean isStr=false;

    IREntity(IRType type){
        this.type=type;
    }

    public abstract String getValue();

    public  String toString(){
        return type.toString();
    }

}
