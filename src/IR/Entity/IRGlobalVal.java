package IR.Entity;

import IR.IRBasicBlock;
import IR.Type.IRPtrType;
import IR.Type.IRType;

public class IRGlobalVal extends IREntity{
    public String name;
    public IREntity init;
    public boolean isString=false;

    public IRGlobalVal(IRType type, String name){
        super(type);
        this.name=name;
    }
    public IRGlobalVal(IRType type, String name,IREntity init){
        super(type);
        this.name=name;
        this.init=init;
    }

    @Override
    public String getValue(){
        return "@" + name;
    }

    @Override
    public String toString() {
        return "ptr "+getValue();
    }

}
