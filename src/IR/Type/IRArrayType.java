package IR.Type;

import IR.Entity.IRConst;
import IR.Entity.IREntity;

public class IRArrayType extends IRType {
    public IRType baseType;  //可以是IRArray本身 从而实现嵌套
    public int cnt;

    public IRArrayType(IRType baseType, int cnt) {
        super("[" + String.valueOf(cnt) + "x" + baseType.name + "]", baseType.size * cnt);
        this.baseType = baseType;
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(cnt) + "x" + baseType.toString() + "]";
    }

    @Override
    public IREntity defaultValue() {
        return new IRConst(this, IRConst.constType.NULL);
    }
}
