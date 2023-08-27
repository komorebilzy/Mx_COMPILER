package Assembly.Instruction;

import Assembly.Operand.Reg;

public class AsmMemoryS extends AsmInst{
    public String op;         //lw sw
    public Reg rd,rs;
    public int offset;

    public AsmMemoryS(String op ,Reg rd,Reg rs,int offset){
        this.op=op;
        this.rd=rd;
        this.rs=rs;
        this.offset=offset;
    }

    @Override
    public String toString() {
        return op+"\t"+rd.toString()+", "+offset+"("+rs.toString()+")";
    }
}
