package Assembly.Instruction;

import Assembly.Operand.Operand;

public class AsmBinaryS extends AsmInst{
    public String op;   //addi add sub mul div
    public Operand rd,rs1,rs2;
    public AsmBinaryS(String op,Operand rd,Operand rs1,Operand rs2){
        this.op=op;
        this.rd=rd;
        this.rs1=rs1;
        this.rs2=rs2;
    }

    @Override
    public String toString() {
        return op + "\t" + rd.toString()+", "+rs2.toString();
    }
}
