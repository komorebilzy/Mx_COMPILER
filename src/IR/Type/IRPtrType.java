package IR.Type;

import IR.Entity.IRConst;
import IR.Entity.IREntity;

public class IRPtrType extends IRType {
    public IRType baseType;
    public int dim;   //指针维数

    public IRPtrType(IRType baseType) {
        super(baseType.name + "*", 4);
        this.baseType = baseType;
        if (baseType instanceof IRPtrType) {
            this.baseType = ((IRPtrType) baseType).baseType;
            this.dim = ((IRPtrType) baseType).dim + 1;
        } else this.dim = 1;
    }

    public IRPtrType(IRType baseType, int dim) {
        super(baseType.name + "*".repeat(dim), 4);
        this.baseType = baseType;
        if (baseType instanceof IRPtrType) {
            this.baseType = ((IRPtrType) baseType).baseType;
            this.dim = ((IRPtrType) baseType).dim + dim;
        } else this.dim = dim;
    }

    @Override
    public String toString() {
        return baseType.toString() + "*".repeat(dim);
    }

    public IRType pointToType() {
        return dim == 1 ? baseType : new IRPtrType(baseType, dim - 1);
    }

    public boolean equals(Object obj){
        if(obj instanceof IRPtrType com){
            return baseType.equals(com.baseType) && dim==com.dim;
        }
        return false;
    }

    @Override
    public IREntity defaultValue() {
        return new IRConst(this, IRConst.constType.NULL);
    }
}
