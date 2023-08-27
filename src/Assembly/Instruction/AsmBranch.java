package Assembly.Instruction;

import Assembly.Operand.Operand;

public class AsmBranch extends AsmInst{
    public String op;  //bnez beqz
    public Operand cond;
    public String toLabel;

    public AsmBranch(String op, Operand cond,String toLabel){
        this.op=op;
        this.cond=cond;
        this.toLabel=toLabel;
    }

    @Override
    public String toString() {
        return op+ "\t" + cond.toString()+ ", "+toLabel;
    }
}
