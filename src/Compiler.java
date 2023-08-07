import AST.ASTVisitor;
import AST.RootNode;
import FrontEnd.ASTBuilder;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import Util.Scope.GlobalScope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class Compiler {
    public static void main(String[] args) throws Exception{
        InputStream input = System.in;
        boolean online=true;

        if(!online) input=new FileInputStream("text.mx");
        try {
            compile(input);
        }
        catch (Error err){
            System.err.println(err.toString());
            throw new RuntimeException();
        }
    }

    public static void compile(InputStream input) throws Exception{
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



    }
}
