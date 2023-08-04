package Util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class position {
    private int row, col;

    public position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public position(Token token) {
        this.row = token.getLine();
        this.col = token.getCharPositionInLine();
    }

    public position(TerminalNode terminal) {
        this(terminal.getSymbol());
    }

    public position(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public String tostring() {
        return row + "," + col;
    }
}
