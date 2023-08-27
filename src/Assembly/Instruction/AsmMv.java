package Assembly.Instruction;

import Assembly.Operand.Reg;

public class AsmMv extends AsmInst{
    public Reg rd,rs;
    public AsmMv(Reg rd, Reg rs){
        this.rd=rd;
        this.rs=rs;
    }

    @Override
    public String toString() {
        return "mv\t"+rd.toString()+", "+rs.toString();
    }
}
