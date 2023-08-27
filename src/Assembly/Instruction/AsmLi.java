package Assembly.Instruction;

import Assembly.Operand.Imm;
import Assembly.Operand.Reg;

public class AsmLi extends AsmInst {
    public Reg rd;
    public Imm imm;

    public AsmLi(Reg rd, Imm imm) {
        this.rd = rd;
        this.imm = imm;
    }

    @Override
    public String toString() {
        return "li\t" + rd.toString() + ", " + imm.toString();
    }
}
