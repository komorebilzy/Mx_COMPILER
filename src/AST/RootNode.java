package AST;

import Util.*;

import java.util.ArrayList;

public class RootNode extends ASTNode {

    public ArrayList<ASTNode> DefList = new ArrayList<>();

    public RootNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }


}
