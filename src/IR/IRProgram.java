package IR;

import IR.Entity.IRGlobalVal;
import IR.Type.IRClassType;
import Util.BuiltinElements;

import java.util.ArrayList;
import java.util.LinkedList;

public class IRProgram implements BuiltinElements {
    public LinkedList<IRFunction> funcList=new LinkedList<>();
    public ArrayList<IRGlobalVal> globalVarList=new ArrayList<>();
    public ArrayList<IRClassType> structTypeList=new ArrayList<>();

    public IRProgram(){

    }
}
