package IR.Type;

import IR.Entity.IRConst;
import IR.Entity.IREntity;
import IR.IRVisitor;
import IR.Type.IRType;

import java.util.ArrayList;
import java.util.HashMap;

public class IRClassType extends IRType {
    //type: name  size
    public ArrayList<IRType> memberType = new ArrayList<>();
    public HashMap<String, Integer> memberOffset = new HashMap<>();
    public boolean isBuilt=false;

    public IRClassType(String name){
        super(name);
    }

    public IRClassType(String name, int size) {
        super(name, size);
    }

    public void addMem(String name, IRType type) {
        memberType.add(type);
        memberOffset.put(name, memberType.size() - 1);
    }

    public boolean hasMem(String name) {
        return memberOffset.containsKey(name);
    }

    public IRType getMemberType(String name) {
        return !memberOffset.containsKey(name) ? null : memberType.get(memberOffset.get(name));
    }

    @Override
    public String toString() {
        return "%class."+name;
//        return "ptr";
    }

    @Override
    public IREntity defaultValue() {
        return new IRConst(this, IRConst.constType.NULL);
    }

    public void accept(IRVisitor visitor){
       visitor.visit(this);
    }
}
