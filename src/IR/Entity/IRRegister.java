package IR.Entity;

import IR.Type.IRType;

public class IRRegister extends IREntity{
    public String name;
    public IRRegister(IRType type, String name){
        super(type);
        this.name=name;
    }

    @Override
    public String getValue(){
        return "%" + name;
    }

    @Override
    public String toString() {
        return type + " " + getValue();
    }
}
