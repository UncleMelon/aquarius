package java8.charpter14;

public class TreeProcessor {

    public static int lookup(String k, int defalutVal, Tree t) {
        if (t == null) return defalutVal;
        if (k.equals(t.key)) return t.val;
        return lookup(k, defalutVal, k.compareTo(t.key) < 0 ? t.left : t.right);
    }

    public static void update(String k, int newVal, Tree t) {
        if (t == null) { }
        else if (k.equals(t.key)) t.val = newVal;
        else update(k, newVal, k.compareTo(t.key) < 0 ? t.left : t.right);
    }


}
