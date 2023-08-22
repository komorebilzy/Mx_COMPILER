import AST.RootNode;
import FrontEnd.ASTBuilder;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import IR.IRProgram;
import MiddleEnd.IRBuilder;
import MiddleEnd.IRPrinter;
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import Util.Scope.GlobalScope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws Exception{
        InputStream input = System.in;
        PrintStream output=System.out;
        boolean online=false;

        if(!online) {
            input=new FileInputStream("src/text.mx");
            output=new PrintStream(new FileOutputStream("test.ll"));
        }
        try {
            compile(input,output);
        }
        catch (Error err){
            System.err.println(err.toString());
            throw new RuntimeException();
        }
    }

    public static void compile(InputStream input, PrintStream output) throws Exception{
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
        new IRPrinter(output).visit(rootIR);
    }
}
