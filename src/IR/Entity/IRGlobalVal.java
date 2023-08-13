package IR.Entity;

import IR.Type.IRType;

public class IRGlobalVal extends IREntity{
    public String name;
    public IRGlobalVal(IRType type, String name){
        super(type);
        this.name=name;
    }

    @Override
    public String getValue(){
        return "@" + name;
    }

    @Override
    public String toString() {
        return type + " " + getValue();
    }

}
