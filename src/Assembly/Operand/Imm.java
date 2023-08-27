package Assembly.Operand;

public class Imm extends Operand{
    public int val;
    public Imm(int val){
        this.val=val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
