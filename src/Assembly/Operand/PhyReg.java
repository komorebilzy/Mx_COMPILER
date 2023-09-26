package Assembly.Operand;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
    public static final PhyReg tp = new PhyReg("tp");  //x4 线程指针
    public static final ArrayList<PhyReg> t = tRegs();  //临时量
    public static final ArrayList<PhyReg> s = sRegs();  //saved register 调用者保存数据
    public static final ArrayList<PhyReg> a = aRegs();  //函数参数/返回值
    public static final PhyReg fp = s(0);    //frame pointer 帧指针
    public static final ArrayList<PhyReg> regs=new ArrayList<>(Arrays.asList(zero,ra,sp,gp,tp));

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

    public static ArrayList<PhyReg> caller = new ArrayList<>();
    public static ArrayList<PhyReg> callee = new ArrayList<>();
    public static ArrayList<Integer> OKColor = new ArrayList<>();
    public static HashSet<PhyReg> allRegs = new HashSet<>();
    public static void addRegCall(int l, int r, ArrayList<PhyReg> regs, ArrayList<PhyReg> call) {
        for (int i = l; i <= r; ++i) {
            call.add(regs.get(i));
        }
    }
    public static void regInitial() {
        allRegs.addAll(PhyReg.s);
        allRegs.addAll(PhyReg.t);
        allRegs.addAll(PhyReg.a);
        allRegs.add(PhyReg.zero);
        allRegs.add(PhyReg.ra);
        allRegs.add(PhyReg.sp);
        allRegs.add(PhyReg.gp);
        allRegs.add(PhyReg.tp);

        //regs：ArrayList<PhyReg> index对应i 对应32个寄存器
        for(int i=0;i<=2;++i) regs.add(t(i));
        for(int i=0;i<=1;++i) regs.add(s(i));
        for(int i=0;i<=7;++i) regs.add(a(i));
        for(int i=2;i<=11;++i) regs.add(s(i));
        for(int i=3;i<=6;++i) regs.add(t(i));
        caller.clear();
        callee.clear();
        caller.add(PhyReg.ra);
        addRegCall(0, 2, PhyReg.t, caller);
        addRegCall(0, 7, PhyReg.a, caller);
        addRegCall(3, 6, PhyReg.t, caller);
        callee.add(PhyReg.sp);
        addRegCall(0, 11, PhyReg.s, callee);
        for (int i = 5; i <= 7; ++i) OKColor.add(i);
        for (int i = 9; i <= 31; ++i) OKColor.add(i);
    }


}
