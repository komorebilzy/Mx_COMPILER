package Assembly.Instruction;

import Assembly.Operand.Operand;

public class AsmCmpS extends AsmInst{
    public String op; //seqz snez  (if rs!=0 ,rs=1;else rd=0)
    public Operand rd,rs;

    public AsmCmpS(String op,Operand rd,Operand rs){
        this.op=op;
        this.rd=rd;
        this.rs=rs;
    }

    @Override
    public String toString() {
        return op+ "\t"+rd.toString()+", "+rd.toString();
    }
}
