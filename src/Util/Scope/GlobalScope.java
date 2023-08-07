package Util.Scope;

import AST.Def.ClassDefNode;
import AST.Def.FuncDefNode;
import Util.Error.semanticError;

import static Util.BuiltinElements.*;
import java.util.HashMap;


public class GlobalScope extends Scope{
    public HashMap<String, ClassDefNode> classMems=new HashMap<>();
    public HashMap<String, FuncDefNode> funcMems=new HashMap<>();

    public GlobalScope(){
        super(null);
        funcMems.put("print",PrintFunc);
        funcMems.put("println",Println);
        funcMems.put("printlnInt",PrintlnInt);
        funcMems.put("printInt",PrintInt);
        funcMems.put("getString",GetString);
        funcMems.put("getInt",GetInt);
        funcMems.put("toString",ToString);

        ClassDefNode stringDef=new ClassDefNode(null,"string");
        stringDef.funcList.add(Length);
        stringDef.funcList.add(SubString);
        stringDef.funcList.add(ParseInt);
        stringDef.funcList.add(Ord);
        classMems.put("string", stringDef);
        classMems.put("int", new ClassDefNode(null, "int"));
        classMems.put("bool", new ClassDefNode(null, "bool"));
    }

    public void addFunc(String name, FuncDefNode value){
        if(funcMems.containsKey(name))
            throw new semanticError(" function redefine", value.pos);
        funcMems.put(name,value);
    }

    public void addClass(String name,ClassDefNode value){
        if(classMems.containsKey(name))
            throw new semanticError(" class redefine", value.pos);
        classMems.put(name,value);
    }
    public FuncDefNode getFunc(String name){
        return funcMems.get(name);
    }
    public ClassDefNode getClass(String name){
        return classMems.get(name);
    }

}
