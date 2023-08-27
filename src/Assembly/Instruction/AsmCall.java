package Assembly.Instruction;

public class AsmCall extends AsmInst{
    public String name;

    public AsmCall(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "call\t"+name;
    }
}
