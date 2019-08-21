package designpatterns.callback.case4;

/**
 * https://blog.csdn.net/lc545126483/article/details/79954612
 * @author matthew_wu
 * @since 2019-08-19 17:47
 */
public class Test {
    public static void main(String[] args) {
        int a = 56;
        int b = 31;
        int c = 23612;
        int d = 13212;
        Student s = new Student("小明");
        Seller s2 = new Seller("老婆婆");
        s.callHelp(a, b);
        s2.callHelp(c, d);
    }
}
