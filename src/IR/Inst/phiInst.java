package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRType;

import java.util.ArrayList;

public class phiInst extends IRInst {
    public IRRegister res;
    IRType type;
    public ArrayList<IREntity> values = new ArrayList<>();
    public ArrayList<IRBasicBlock> blocks = new ArrayList<>();

    public phiInst(IRBasicBlock par, IRRegister res) {
        super(par);
        this.res = res;
        this.type = res.type;
    }

    public void add(IREntity value, IRBasicBlock block) {
        values.add(value == null ? type.defaultValue() : value);
        blocks.add(block);
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String ans = res.getValue() + " = phi " + type.toString() + " ";
        for (int i = 0; i < values.size(); ++i) {
            ans += "[ " + values.get(i).getValue() + ", %" + blocks.get(i).name + " ]";
            if (i != values.size() - 1) ans += ", ";
        }
        return ans;
    }
}
