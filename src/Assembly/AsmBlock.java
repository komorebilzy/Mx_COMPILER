package Assembly;

import Assembly.Instruction.AsmInst;
import Assembly.Operand.Reg;

import java.util.ArrayList;
import java.util.HashSet;

public class AsmBlock {
    public AsmInst headInst=null,tailInst=null;
    public String label;
    public boolean isReturned=false;

    public AsmBlock(String label){
        this.label=label;
    }

    public void push_back(AsmInst inst){
        if(headInst==null) headInst=tailInst=inst;
        else{
            tailInst.next=inst;
            inst.prev=tailInst;
            tailInst=inst;
        }
    }

    public void add_front(AsmInst inst){
        if(headInst==null) headInst=inst;
        else{
            headInst.prev=inst;
            inst.next=headInst;
            headInst=inst;
        }
    }

    public void insert_before(AsmInst inst,AsmInst inserted){
        if(inst.prev == null) headInst=inserted;
        else inst.prev.next=inserted;
        inserted.prev=inst.prev;
        inserted.next=inst;
        inst.prev=inserted;
    }

    public void insert_after(AsmInst inst,AsmInst inserted){
        if(inst.next==null) tailInst=inst;
        else inst.next.prev=inserted;
        inserted.next=inst.next;
        inserted.prev=inst;
        inst.next=inserted;
    }

    public String toString(){
        StringBuilder ans=new StringBuilder();
        if(label!=null) ans.append(label).append(":\n");
        for (AsmInst inst=headInst;inst!=null;inst=inst.next){
            ans.append("\t").append(inst.toString()).append("\n");
        }
        return ans.toString();
    }

    //for optimize
    public ArrayList<AsmBlock> pred=new ArrayList<>(); //前序块
    public ArrayList<AsmBlock> succ=new ArrayList<>();  //后序块
    public HashSet<Reg> use=new HashSet<>();
    public HashSet<Reg> def=new HashSet<>();

    public void add_pred(AsmBlock it){
        pred.add(it);
    }

    public void add_succ(AsmBlock it){
        succ.add(it);
    }
}
