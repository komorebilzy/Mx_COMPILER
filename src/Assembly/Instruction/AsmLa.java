package Assembly.Instruction;

import Assembly.Operand.Reg;

public class AsmLa extends AsmInst{
    public Reg rd;
    public String name;
    public AsmLa(Reg rd,String name){
        this.rd=rd;
        this.name=name;
    }

    @Override
    public String toString() {
        return "la\t"+rd.toString()+", "+name;
    }
}
