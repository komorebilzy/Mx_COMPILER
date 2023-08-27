package BackEnd;

import Assembly.AsmModule;

import java.io.PrintStream;

public class AsmPrinter {
    private final PrintStream out;
    public AsmPrinter(PrintStream out){
        this.out=out;
    }

    public void print(AsmModule module){
        if(out==null) return;
        out.print(module.toString());
    }
}
