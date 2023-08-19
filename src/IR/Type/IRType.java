package IR.Type;

import IR.Entity.IREntity;
import Util.BuiltinElements;

import java.security.PublicKey;

public abstract class IRType implements BuiltinElements {
    public String name;
    public int size;  // cnt of bit

    public IRType(String name) {
        this.name = name;
    }

    public IRType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public abstract String toString();
    public abstract IREntity defaultValue();

    public IRPtrType asPtr(){
        return new IRPtrType(this);
    }

    public IRType pointToType() {
        return irNullType;
    }

}
