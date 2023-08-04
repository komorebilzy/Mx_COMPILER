package Util.Scope;

import AST.Def.ClassDefNode;
import Util.Error.semanticError;
import Util.Type;
import Util.position;

import java.util.HashMap;

public class Scope {
    public HashMap<String, Type> varMembers = new HashMap<>();
    public Scope parentScope;
    public Type returnType = null;
    public ClassDefNode inWhichClass = null;
    public boolean lookUp = false, isReturned = false;

    public Scope(Scope par) {
        parentScope = par;
    }

    public Scope ParentScope() {
        return parentScope;
    }

    public void addVar(String name, Type t, position pos) {
        if (varMembers.containsKey(name))
            throw new semanticError("Semantic Error: variable redefine", pos);
        varMembers.put(name, t);
    }

    public Type getType(String name) {
        if (varMembers.containsKey(name)) return varMembers.get(name);
        else {
            return parentScope != null ? parentScope.getType(name) : null;
        }
    }
    public boolean hasValInThisScope(String name){
        return varMembers.containsKey(name);
    }

}
