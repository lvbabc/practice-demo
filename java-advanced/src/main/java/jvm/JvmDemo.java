package jvm;

public class JvmDemo {
    public static void main(String[] args) {
        String s = new String("吕兵1");
        String s0 = s.intern();
        String s2 = "吕兵1";
        System.out.println(s0 == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);

        String ss1 = new String("xyz");
        String ss2 = new String("xyz");

        System.out.println(ss1.intern() == ss2.intern());
    }
}
