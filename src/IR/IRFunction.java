package IR;

import IR.Entity.IREntity;
import IR.Entity.IRRegister;
import IR.Type.IRType;
import IR.Type.IRVoidType;
import MiddleEnd.IRBuilder;
import Util.BuiltinElements;
import IR.Inst.*;

import javax.swing.text.html.parser.Entity;
import java.rmi.registry.Registry;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class IRFunction {
    public String name;
    public IRType returnType;
    public IREntity returnEnt;
    public IRBasicBlock entry=new IRBasicBlock("entry");
    public ArrayList<IREntity> params = new ArrayList<>();
    public ArrayList<IRBasicBlock> blocks = new ArrayList<>();
    public int labelNum=-1;
    public int var=-1;
    public boolean isReturned=false;

    public IRFunction(String name, IRType returnType) {
        this.name =  name;
        this.returnType = returnType;
    }

    public IRFunction(String name, IRType returnType, IREntity... para) {
        this.name =  name;
        this.returnType = returnType;
        params.addAll(List.of(para));
    }

    public String toString() {
        StringBuilder ans = new StringBuilder("define " + returnType.toString() + " @" + name + "(");
        for(int i=0;i<params.size();++i){
            ans.append(params.get(i).toString());
            if(i!=params.size()-1) ans.append(", ");
        }
        ans.append(") {\n");
        for(IRBasicBlock block:blocks){
            ans.append(block.toString()).append("\n");
        }
//        if(returnBlock!=null) ans.append(returnBlock.toString()).append("\n");
        ans.append("}\n");
        return ans.toString();
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

    public static IRFunction globalVarInit(String name){
        IRFunction func=new IRFunction("_mx_global_var_init_of_"+name, BuiltinElements.irVoidType);
        return func;
    }

    public String getLabel(){
        return "block_"+Integer.toString(++labelNum);
    }

    public String getRegId(){
        return  Integer.toString(++var);
    }

}
