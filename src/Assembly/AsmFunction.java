package Assembly;

import Assembly.Instruction.*;
import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

import java.util.HashMap;
import java.util.LinkedList;

import static Assembly.Operand.PhyReg.*;

public class AsmFunction {
    public String name;
    public LinkedList<AsmBlock> blocks = new LinkedList<>();
    public int offset = 8, paraOffset = 0;
    public HashMap<Reg, Integer> stack = new HashMap<>();

    private static int cnt = 0;
    public final int id = cnt++;

    public AsmFunction(String funcName) {
        name = funcName;
    }

    public void addBlock(AsmBlock block) {
        blocks.add(block);
    }

    public String toString() {
        StringBuilder ans = new StringBuilder("\t.globl " + name + "\n");
        for (AsmBlock block : blocks) {
            ans.append(block.toString());
        }
        return ans.toString();
    }

    public void allocate(Reg reg) {
        stack.put(reg, offset += 4);
    }

    public int getVarRegOffset(Reg reg) {
        return -stack.get(reg);
    }

    public boolean containsReg(Reg reg) {
        return stack.containsKey(reg);
    }

    public void savecall() {
        AsmBlock firstBlock = blocks.getFirst();
        HashMap<Reg, Integer> mapp = new HashMap<>();
        for (var call1 : callee) {
            firstBlock.add_front(new AsmMemoryS("sw", call1, fp, -(offset += 4)));
            mapp.put(call1, offset);
        }
        for (var block : blocks) {
            if (block.isReturned) {
                AsmInst inst=block.tailInst;
                for (var call1 : callee) {
                    block.insert_before(inst, new AsmMemoryS("lw", call1, fp, -mapp.get(call1)));
                }
            }
        }
//        for (var block : blocks) {
//            for (var inst = block.headInst; inst != null; inst = inst.next) {
//                if (inst instanceof AsmCall) {
//                    for (var call : caller) {
//                        block.insert_before(inst, new AsmMemoryS("sw", call, fp, -(offset += 4)));
//                        block.insert_after(inst, new AsmMemoryS("lw", call, fp, -offset));
//                    }
//                    block.insert_after(inst,new AsmMv(gp,a(0)));
//                }
//            }
//        }
    }

    public void finish() {
        int allOffset = offset + paraOffset;
        //sp 始终保持 16 字节对齐
        int spOffset = (allOffset % 16 == 0) ? allOffset : (((allOffset >> 4) + 1) << 4);

        AsmBlock firstBlock = blocks.getFirst();
        if (spOffset > 2048) {
            var t0 = t(0);
            var t1 = t(1);
            firstBlock.add_front(new AsmBinaryS("add", fp, sp, t0));
            firstBlock.add_front(new AsmMemoryS("sw", fp, t1, -8));
            firstBlock.add_front(new AsmMemoryS("sw", ra, t1, -4));
            firstBlock.add_front(new AsmBinaryS("add", t1, sp, t0));
            firstBlock.add_front(new AsmBinaryS("sub", sp, sp, t0));
            firstBlock.add_front(new AsmLi(t0, new Imm(spOffset)));
        } else {
            //调整栈指针和保存寄存器的值
            //fp : 堆栈帧是一个包含函数参数、局部变量和其他与函数调用相关的信息的区域。fp 指向堆栈帧的底部或开始位置
            firstBlock.add_front(new AsmBinaryS("addi", fp, sp, new Imm(spOffset)));
            firstBlock.add_front(new AsmMemoryS("sw", fp, sp, spOffset - 8));
            firstBlock.add_front(new AsmMemoryS("sw", ra, sp, spOffset - 4));
            firstBlock.add_front(new AsmBinaryS("addi", sp, sp, new Imm(-spOffset)));
        }

        // for the functions that contains many return sentence
        for (var lastBlock : blocks) {
            if (lastBlock.isReturned) {
                AsmInst lastInst = lastBlock.tailInst;
                if (spOffset > 2048) {
                    var t0 = t(0);
                    var t1 = t(1);
                    lastBlock.insert_before(lastInst, new AsmLi(t0, new Imm(spOffset)));
                    lastBlock.insert_before(lastInst, new AsmBinaryS("add", t1, sp, t0));
                    lastBlock.insert_before(lastInst, new AsmMemoryS("lw", fp, t1, -8));
                    lastBlock.insert_before(lastInst, new AsmMemoryS("lw", ra, t1, -4));
                    lastBlock.insert_before(lastInst, new AsmBinaryS("add", sp, sp, t0));
                } else {
                    //恢复寄存器的值和还原栈指针
                    lastBlock.insert_before(lastInst, new AsmMemoryS("lw", fp, sp, spOffset - 8));
                    lastBlock.insert_before(lastInst, new AsmMemoryS("lw", ra, sp, spOffset - 4));
                    lastBlock.insert_before(lastInst, new AsmBinaryS("addi", sp, sp, new Imm(spOffset)));
                }
            }
        }
    }

}
