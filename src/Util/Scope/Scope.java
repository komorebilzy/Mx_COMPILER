package Util.Scope;

import AST.Def.ClassDefNode;
import IR.Entity.IREntity;
import Util.Error.semanticError;
import Util.Type;
import Util.position;

import java.util.HashMap;

public class Scope {
    public HashMap<String, Type> varMembers = new HashMap<>();
    public Scope parentScope;
    public Type returnType = null; //belong to a function
    public ClassDefNode inWhichClass = null;  //belong to a class
    public boolean inLoop = false, isReturned = false;

    public Scope(Scope par) {
        parentScope = par;
        if (par != null) {
            if (par.inWhichClass != null) inWhichClass = par.inWhichClass;
            inLoop = par.inLoop;
        }
    }

    public Scope(Scope par, ClassDefNode in) {
        parentScope = par;
        inWhichClass = in;
    }

    public Scope(Scope par, Type type) {
        parentScope = par;
        returnType = type;
        if (par.inWhichClass != null) inWhichClass = par.inWhichClass;
        inLoop = par.inLoop;
    }

    public Scope(Scope par, boolean inloop) {
        parentScope = par;
        inLoop = inloop;
        if (par.inWhichClass != null) inWhichClass = par.inWhichClass;
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

    public boolean hasValInThisScope(String name) {
        return varMembers.containsKey(name);
    }

    //==========================================IR=====================================================

    public HashMap<String, IREntity> entities = new HashMap<>();

    public void addVar(String name, IREntity ent) {
        entities.put(name, ent);
    }

    public IREntity getIRVar(String name) {
        if (entities.containsKey(name)) return entities.get(name);
        return parentScope != null ? parentScope.getIRVar(name) : null;
    }
}
