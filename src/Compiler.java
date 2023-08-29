import AST.RootNode;
import Assembly.AsmModule;
import BackEnd.AsmPrinter;
import BackEnd.InsSelector;
import BackEnd.RegAlloca;
import FrontEnd.ASTBuilder;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import IR.IRProgram;
import MiddleEnd.IRBuilder;
import MiddleEnd.IRPrinter;
import Parser.MxLexer;
import Parser.MxParser;
import Util.Error.codgenError;
import Util.MxErrorListener;
import Util.Scope.GlobalScope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws Exception{
        InputStream input = System.in;
        PrintStream IROutput = null;
        PrintStream AsmOutput = System.out;
        boolean online=false;

        if(!online) {
            input=new FileInputStream("src/text.mx");
            IROutput = new PrintStream(new FileOutputStream("test.ll"));
            AsmOutput = new PrintStream(new FileOutputStream("test.s"));
        }
        try {
            compile(input,IROutput,AsmOutput);
        }
        catch (Error err){
            System.err.println(err.toString());
            throw new RuntimeException();
        }
    }

    public static void compile(InputStream input, PrintStream IROutput, PrintStream AsmOutput) throws Exception{
        GlobalScope globalScope= new GlobalScope();

        MxLexer lexer=new MxLexer(CharStreams.fromStream(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new MxErrorListener());

        MxParser parser=new MxParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new MxErrorListener());
        MxParser.ProgramContext parserTreeRoot=parser.program();

        ASTBuilder astbuilder=new ASTBuilder();
        RootNode ASTRoot=(RootNode) astbuilder.visit(parserTreeRoot);

        new SymbolCollector(globalScope).visit(ASTRoot);
        new SemanticChecker(globalScope).visit(ASTRoot);

        IRProgram rootIR=new IRProgram();
        new IRBuilder(rootIR,globalScope).visit(ASTRoot);
        new IRPrinter(IROutput).visit(rootIR);

        AsmModule asmModule = new AsmModule();
        try{
            new InsSelector(asmModule).visit(rootIR);
        }
        catch (Error err){
            throw new codgenError("hhh",null);
        }
        try {
            new RegAlloca().visit(asmModule);
        }
        catch (Error err){
            throw new codgenError("yyy",null);
        }
        try {
            new AsmPrinter(AsmOutput).print(asmModule);
        }
        catch (Error err){
            throw new codgenError("xxx",null);
        }
    }
}
