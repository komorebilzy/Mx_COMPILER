package Assembly.Instruction;

import Assembly.Operand.Reg;

import java.util.HashSet;

public abstract class AsmInst {
    public AsmInst prev = null, next = null;
    public HashSet<Reg> use=new HashSet<>();
    public HashSet<Reg> def=new HashSet<>();

    public String toString() {
        return null;
    }
}
