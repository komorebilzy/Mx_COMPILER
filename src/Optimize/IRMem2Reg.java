package Optimize;

import IR.Entity.IREntity;
import IR.Entity.IRGlobalVal;
import IR.Entity.IRRegister;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRProgram;
import IR.IRVisitor;
import IR.Inst.*;
import IR.Type.IRClassType;
import IR.Type.IRPtrType;

import java.util.ArrayList;
import java.util.HashMap;

public class IRMem2Reg implements IRVisitor {
    IRProgram rootIR;
    int cnt;
    HashMap<String, IREntity> MemToReg = new HashMap<>();

    IREntity getReg(IREntity irValue) {
        if (!(irValue instanceof IRRegister)) return irValue;
        String name = ((IRRegister) irValue).name;
        if (name == null || !MemToReg.containsKey(name)) return irValue;
        return MemToReg.get(name);
    }

    public IRMem2Reg(IRProgram rootIR) {
        this.rootIR = rootIR;
    }

    public void removeLoadInst(ArrayList<loadInst> Insts,storeInst sInst){
        for(var lInst:Insts){
            lInst.basicBlock.insts.remove(lInst);
            MemToReg.put(lInst.res.getValue(),sInst.value);
        }
        visit(rootIR);
    }

    public void work(){
        for(var func:rootIR.funcList){
            for(var alloca:func.allocas){
                //没有use 直接删除
                if(!func.loadIns.containsKey(alloca.res.getValue())){
                    func.allocas.remove(alloca);
                }
                //只被def一次
                if(func.storeIns.get(alloca.res.getValue()).size()==1){
                    func.allocas.remove(alloca);
                    removeLoadInst(func.loadIns.get(alloca.res.getValue()),func.storeIns.get(alloca.res.getValue()).get(0));
                }
                //在同一个块内被多次定义
                //todo

                //不同分支的phi
                //todo
            }
        }
    }

    @Override
    public void visit(IRProgram it) {
        for (var func : it.funcList) {
            func.accept(this);
        }
    }

    @Override
    public void visit(IRFunction it) {
        MemToReg = new HashMap<>();
        for (var block : it.blocks) {
            block.accept(this);
        }
    }

    @Override
    public void visit(IRBasicBlock it) {
        for (var inst : it.insts) {
            inst.accept(this);
        }
    }


    @Override
    public void visit(IRClassType it) {

    }

    @Override
    public void visit(IRGlobalVal it) {

    }

    @Override
    public void visit(allocateInst it) {
        MemToReg.put(it.res.name, new IRRegister(it.res.type, "_reg_" + cnt++));
    }

    @Override
    public void visit(binaryInst it) {
        it.lhs=getReg(it.lhs);
        it.rhs=getReg(it.rhs);
    }

    @Override
    public void visit(brInst it) {
        it.cond=getReg(it.cond);
    }

    @Override
    public void visit(callInst it) {
        ArrayList<IREntity> paras=new ArrayList<>();
        for(var para:it.args){
            paras.add(getReg(para));
        }
        it.args=paras;
        it.res=(IRRegister) getReg(it.res);
    }

    @Override
    public void visit(jumpInst it) {

    }

    @Override
    public void visit(getelementptrInstr it) {
        it.res=(IRRegister) getReg(it.res);
        it.ptr=getReg(it.ptr);
        it.indexList.forEach(this::getReg);
    }

    @Override
    public void visit(lcmpInst it) {
        it.lhs=getReg(it.lhs);
        it.rhs=getReg(it.rhs);
    }

    @Override
    public void visit(loadInst it) {
        it.pointer=getReg(it.pointer);
        it.res=(IRRegister) getReg(it.res);
    }

    @Override
    public void visit(storeInst it) {
        it.value=getReg(it.value);
        it.pointer=getReg(it.pointer);
    }

    @Override
    public void visit(phiInst it) {

    }

    @Override
    public void visit(retInst it) {
        it.value=getReg(it.value);
    }

    @Override
    public void visit(selectInst it) {

    }

    @Override
    public void visit(declareInst it) {

    }
}
