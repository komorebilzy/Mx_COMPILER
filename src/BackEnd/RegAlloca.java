package BackEnd;

import Assembly.AsmBlock;
import Assembly.AsmFunction;
import Assembly.AsmModule;
import Assembly.Instruction.*;
import Assembly.Operand.*;
import Util.Error.codgenError;

import static Assembly.Operand.PhyReg.*;


public class RegAlloca {
    private AsmFunction curFunction;
    private AsmBlock curBlock;

    private Operand allocatePhyReg(AsmInst ins, Operand operand, PhyReg reg, boolean isLoad) {
        if (operand instanceof VirReg v) {
            if (!curFunction.containsReg(v)) curFunction.allocate(v);
            int offset = curFunction.getVarRegOffset(v);

            if (-2048 < offset && offset < 2048) {
                if (isLoad) curBlock.insert_before(ins, new AsmMemoryS("lw", reg, fp, offset));
                else curBlock.insert_after(ins, new AsmMemoryS("sw", reg, fp, offset));
            } else {
                PhyReg tfp = t(4);
                if (isLoad) {
                    curBlock.insert_before(ins, new AsmLi(tfp, new Imm(offset)));
                    curBlock.insert_before(ins, new AsmBinaryS("add", tfp, fp, tfp));
                    curBlock.insert_before(ins, new AsmMemoryS("lw", reg, tfp, 0));
                } else {
                    curBlock.insert_after(ins, new AsmMemoryS("sw", reg, tfp, 0));
                    curBlock.insert_after(ins, new AsmBinaryS("add", tfp, fp, tfp));
                    curBlock.insert_after(ins, new AsmLi(tfp, new Imm(offset)));
                }
            }
            return reg;
        } else return operand;
    }

    public void visit(AsmModule it) {
        it.functions.forEach(this::visit);
    }

    public void visit(AsmFunction it) {
        curFunction = it;
        it.blocks.forEach(this::visit);
        it.finish();
    }

    public void visit(AsmBlock it) {
        curBlock = it;
        //最后一条指令不需要收集 jump or ret
        for (AsmInst inst = it.headInst; inst != it.tailInst; inst = inst.next) visit(inst);
    }

    public void visit(AsmInst it) {
        if (it instanceof AsmBinaryS ins) visit(ins);
        else if (it instanceof AsmLi ins) visit(ins);
        else if (it instanceof AsmLa ins) visit(ins);
        else if (it instanceof AsmMemoryS ins) visit(ins);
        else if (it instanceof AsmMv ins) visit(ins);
        else if (it instanceof AsmCmpS ins) visit(ins);
        else if(it instanceof AsmBranch ins) visit(ins);
    }

    public void visit(AsmBinaryS it) {
        //先load后store    a=a+1
        it.rs1 = allocatePhyReg(it, it.rs1, t(0), true);
        it.rs2 = allocatePhyReg(it, it.rs2, t(1), true);
        it.rd = allocatePhyReg(it, it.rd, t(2), false);
    }

    public void visit(AsmLi it){
        it.rd=(Reg)allocatePhyReg(it,it.rd,t(0),false);
    }

    public void visit(AsmLa it){
        it.rd=(Reg) allocatePhyReg(it,it.rd,t(0),false);
    }

    public void visit(AsmBranch it){
        it.cond=allocatePhyReg(it,it.cond,t(0),true);
    }

    public void visit(AsmMemoryS it){
        if(it.op.equals("sw")){
            it.rs=(Reg) allocatePhyReg(it,it.rs,t(0),true);
            it.rd=(Reg) allocatePhyReg(it,it.rd,t(1),true);
        }
        else{
            it.rs=(Reg) allocatePhyReg(it,it.rs,t(0),true);
            it.rd=(Reg) allocatePhyReg(it,it.rd,t(1),false);
        }
    }

    public void visit(AsmMv it){
        it.rd=(Reg) allocatePhyReg(it,it.rd,t(0),false);
        it.rs=(Reg) allocatePhyReg(it,it.rs,t(1),true);
    }

    public void visit(AsmCmpS it){
        it.rd=allocatePhyReg(it,it.rd,t(0),false);
        it.rs=allocatePhyReg(it,it.rs,t(1),true);
    }



}
