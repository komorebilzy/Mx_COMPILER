package IR.Inst;

import IR.Entity.IREntity;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

import java.util.ArrayList;
import java.util.Arrays;

public class declareInst extends IRInst {

    public IRType returnType;
    public String funcName;
    public ArrayList<IREntity> params = new ArrayList<>();

    public declareInst(IRBasicBlock block, IRType type, String name, IREntity... para) {
        super(block);
        returnType = type;
        funcName = name;
        params.addAll(Arrays.asList(para));
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String ans = "declare " + returnType.toString() +" @"+funcName+ "(";
        if (params != null || params.size() != 0) {
            for (int i = 0; i < params.size(); ++i) {
                ans += params.get(i).toString();
                if (i != params.size() - 1) ans += ", ";
            }
        }
        ans += ")";
        return ans;
    }

}
