package IR.Inst;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Type.IRPtrType;
import IR.Type.IRType;

import java.util.ArrayList;
import java.util.Arrays;

public class getelementptrInstr extends IRInst{
    public IRRegister res;
    public IRType pointType;
    public IREntity ptr;
    public ArrayList<IREntity> indexList=new ArrayList<>();

    public getelementptrInstr(IRBasicBlock block, IRRegister res, IREntity ptr, IREntity...indexList) {
        super(block);
        this.res=res;
        this.ptr=ptr;
        this.pointType=((IRPtrType)ptr.type).pointToType();
        this.indexList.addAll(Arrays.asList(indexList));

    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String ans=res.getValue()+" = getelementptr "+pointType.toString()+", "+ptr.toString();
        for(int i=0;i<indexList.size();++i){
            ans+=", "+indexList.get(i).toString();
        }
        return ans;
    }
}
