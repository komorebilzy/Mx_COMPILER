package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

import java.util.ArrayList;
import java.util.Arrays;

public class callInst extends IRInst {
    public IRType returnType;
    public ArrayList<IREntity> args = new ArrayList<>();
    public String funcName;
    public IRRegister res;

    public callInst(IRBasicBlock par, IRType type, String name) {
        super(par);
        this.returnType = type;
        this.funcName = name;
    }

    public callInst(IRBasicBlock par, IRType type, IRRegister res, String name, IREntity... args) {
        super(par);
        this.returnType = type;
        this.funcName = name;
        this.res = res;
//        for(IREntity arg:args){
//            this.args.add(arg);
//        }
        this.args.addAll(Arrays.asList(args));
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String ans = (res != null ? res.getValue() + " = call " : "call ") + returnType.toString() + " @" + funcName + "(";
        for (int i = 0; i < args.size(); ++i) {
            ans += args.get(i).toString();
            if (i != args.size() - 1) ans += ", ";
        }
        ans += ")";
        return ans;
    }
}