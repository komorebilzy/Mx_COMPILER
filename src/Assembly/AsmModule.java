package Assembly;

import java.util.ArrayList;

public class AsmModule {
    public ArrayList<AsmData> datas=new ArrayList<>();
    public ArrayList<AsmFunction> functions=new ArrayList<>();

    public void addData(AsmData data){
        datas.add(data);
    }
    public void addFunction(AsmFunction function){
        this.functions.add(function);
    }

    public String toString(){
        StringBuilder ans=new StringBuilder("\t.section\t.text\n");
        for(AsmFunction func :functions){
            ans.append(func.toString()).append("\n");
        }
        for(AsmData data:datas){
            ans.append(data.toString()).append("\n");
        }
        return ans.toString();
    }
}
