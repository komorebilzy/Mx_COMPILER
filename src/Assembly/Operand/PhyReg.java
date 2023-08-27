package Assembly.Operand;

import java.security.PublicKey;
import java.util.ArrayList;

public class PhyReg extends Reg {
    public String name;

    public PhyReg(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final PhyReg zero = new PhyReg("zero");  //x0 始终为0
    public static final PhyReg ra = new PhyReg("ra");  //x1 返回地址
    public static final PhyReg sp = new PhyReg("sp");  //x2 栈指针 stack pointer
    public static final PhyReg gp = new PhyReg("gp");  //x3 全局指针 global pointer
    public static final ArrayList<PhyReg> t = tRegs();  //临时量
    public static final ArrayList<PhyReg> s = sRegs();  //saved register 调用者保存数据
    public static final ArrayList<PhyReg> a = aRegs();  //函数参数/返回值
    public static final PhyReg fp = s(0);    //frame pointer 帧指针

    static public PhyReg t(int i) {
        return t.get(i);
    }

    static public PhyReg s(int i) {
        return s.get(i);
    }

    static public PhyReg a(int i) {
        return a.get(i);
    }

    static private ArrayList<PhyReg> tRegs() {
        ArrayList<PhyReg> tRegs = new ArrayList<>();
        for (int i = 0; i <= 6; ++i) {
            tRegs.add(new PhyReg("t" + i));
        }
        return tRegs;
    }

    static private ArrayList<PhyReg> sRegs() {
        ArrayList<PhyReg> sRegs = new ArrayList<>();
        for (int i = 0; i <= 11; ++i) {
            sRegs.add(new PhyReg("s" + i));
        }
        return sRegs;
    }

    static private ArrayList<PhyReg> aRegs() {
        ArrayList<PhyReg> aRegs = new ArrayList<>();
        for (int i = 0; i <= 7; ++i) {
            aRegs.add(new PhyReg("a" + i));
        }
        return aRegs;
    }


}
