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

    public void finish() {
        int allOffset = offset + paraOffset;
        //sp 始终保持 16 字节对齐
        int spOffset = (allOffset % 16 == 0) ? allOffset :( ((allOffset >> 4) + 1) << 4);
        AsmBlock lastBlock=blocks.getLast();
        AsmBlock firstBlock=blocks.getFirst();
        AsmInst lastInst=lastBlock.tailInst;

        if(spOffset > 2048){
            var t0=t(0);
            var t1=t(1);
            firstBlock.add_front(new AsmBinaryS("add",fp,sp,t0));
            firstBlock.add_front(new AsmMemoryS("sw",fp,t1,-8));
            firstBlock.add_front(new AsmMemoryS("sw",ra,t1,-4));
            firstBlock.add_front(new AsmBinaryS("add",t1,sp,t0));
            firstBlock.add_front(new AsmBinaryS("sub",sp,sp,t0));
            firstBlock.add_front(new AsmLi(t0,new Imm(spOffset)));

            lastBlock.insert_before(lastInst,new AsmLi(t0,new Imm(spOffset)));
            lastBlock.insert_before(lastInst,new AsmBinaryS("add",t1,sp,t0));
            lastBlock.insert_before(lastInst,new AsmMemoryS("lw",fp,t1,-8));
            lastBlock.insert_before(lastInst,new AsmMemoryS("lw",ra,t1,-4));
            lastBlock.insert_before(lastInst,new AsmBinaryS("add",sp,sp,t0));
        }

        else{
            //调整栈指针和保存寄存器的值
            firstBlock.add_front(new AsmBinaryS("addi",fp,sp,new Imm(spOffset)));
            firstBlock.add_front(new AsmMemoryS("sw",fp,sp,spOffset-8));
            firstBlock.add_front(new AsmMemoryS("sw",ra,sp,spOffset-4));
            firstBlock.add_front(new AsmBinaryS("addi",sp,sp,new Imm(-spOffset)));

            //恢复寄存器的值和还原栈指针
            lastBlock.insert_before(lastInst,new AsmMemoryS("lw",fp,sp,spOffset-8));
            lastBlock.insert_before(lastInst,new AsmMemoryS("lw",ra,sp,spOffset-4));
            lastBlock.insert_before(lastInst,new AsmBinaryS("addi",sp,sp,new Imm(spOffset)));
        }


    }

}
