package Assembly.Instruction;

public class AsmJ extends AsmInst{
    public String label;

    public AsmJ(String label){
        this.label=label;
    }

    @Override
    public String toString() {
        return "j\t"+ label;
    }
}
