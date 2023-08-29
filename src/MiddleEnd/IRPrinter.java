package MiddleEnd;

import IR.Entity.IRConst;
import IR.Entity.IRGlobalVal;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRProgram;
import IR.IRVisitor;
import IR.Inst.*;
import IR.Type.IRClassType;

import java.io.PrintStream;

public class IRPrinter implements IRVisitor {
    public PrintStream os;

    public IRPrinter(PrintStream os) {
        this.os = os;
    }

    @Override
    public void visit(IRProgram it) {
        if(os==null) {
            it.funcList.forEach(IRFunction::addAlloca);
            return;
        }
        it.DeclareList.forEach(dec->os.println(dec.toString()));
        for(int i=0;i<it.globalVarList.size();++i){
            var vara=it.globalVarList.get(i);
            if(vara.isString)  os.println( vara.getValue()+" = "+vara.init.toString()+"\n");
            else os.println(vara.getValue()+" = global "+vara.init.toString()+"\n");
        }
        it.structTypeList.forEach(c->c.accept(this));
        it.funcList.forEach(func->func.accept(this));
    }

    @Override
    public void visit(IRClassType it) {
        String ans= "%class." + it.name + " = type { ";
        for(int i=0;i<it.memberType.size();++i){
            ans+=it.memberType.get(i).toString();
            if(i!=it.memberType.size()-1) ans+=", ";
        }
        ans+=" }\n";
        os.println(ans);
    }

    @Override
    public void visit(IRGlobalVal it) {

    }

    @Override
    public void visit(IRFunction it) {
        os.println(it.toString());
        os.println();
    }

    @Override
    public void visit(IRBasicBlock it) {

    }
    @Override
    public void visit(allocateInst it) {

    }

    @Override
    public void visit(binaryInst it) {

    }

    @Override
    public void visit(brInst it) {

    }

    @Override
    public void visit(callInst it) {

    }

    @Override
    public void visit(jumpInst it) {

    }

    @Override
    public void visit(getelementptrInstr it) {

    }

    @Override
    public void visit(lcmpInst it) {

    }

    @Override
    public void visit(loadInst it) {

    }

    @Override
    public void visit(storeInst it) {

    }

    @Override
    public void visit(phiInst it) {

    }

    @Override
    public void visit(retInst it) {

    }

    @Override
    public void visit(selectInst it) {

    }

    @Override
    public void visit(declareInst it) {

    }
}
