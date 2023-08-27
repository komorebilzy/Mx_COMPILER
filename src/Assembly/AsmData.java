package Assembly;

public class AsmData {
    public String name;
    public int val=0;
    public String str=null;
    private final boolean isString;

    public AsmData(String data_name,int value){
        name=data_name;
        val=value;
        isString=false;
    }

    public AsmData(String data_name,String str){
        name=data_name;
        this.str=str;
        isString=true;
    }

    public AsmData(String name,String str,boolean isString){
        this.name=name;
        this.str=str;
        this.isString=isString;
    }


    public String toString(){
        StringBuilder ans=new StringBuilder("\tsection\t.");
        String dataType=isString?"rodata":"data";     //rodata:只读数据  data：可读可写数据
        ans.append(dataType).append("\n");
        String siz =isString?"asciz":"word";   //asciz:字符串
        ans.append(name).append(":\n\t.").append(siz).append("\t");

        if(isString) ans.append("\"").append(str).append("\"");
        else if(str!=null) ans.append(str);
        else ans.append(val);

        ans.append("\n");
        return ans.toString();
    }
}
