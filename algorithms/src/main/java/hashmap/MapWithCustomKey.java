package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * java自定义类型 作为HashMap中的Key值 （Pair<V,K>为例）
 * 由于是自定义类型，所以HashMap中的equals()方法和hashCode()方法都需要自定义覆盖。
 * 不然内容相同的对象对应的hashCode会不同，无法发挥算法的正常功能，覆盖equals方法，应该就相当于c++重载==运算符来保证能判断是否相等。
 * 只不过java没有自定义重载运算符这个功能的，需要进行方法覆盖。
 * equals的方法原型是 boolean equals(Object o);注意括号内，因为是继承自Object类，覆盖的是超类的方法。hashCode的方法原型就是int hashCode();
 *
 * hash判断数据是否相等  (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))) hash相等，key值相等或者是同一对象
 * @author matthew_wu
 * @since 2019-08-15 17:22
 */
public class MapWithCustomKey {
    public static void main(String[] args) {
        Pair<String, String> p = new Pair<>("a", "b");
        Pair<String, String> q = new Pair<>("a", "b");
        System.out.println(p.equals(q));
        System.out.println(p.hashCode() + " " + q.hashCode());
        Map<Pair<String, String>, Integer> map = new HashMap<>();
        map.put(p, 1);
        System.out.println(map.containsKey(q));
        map.put(q, 2);
        for (Pair<String, String> key: map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}


class Pair<K, V> {
    V first;
    K second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(V first, K second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  Pair)) {
            return false;
        }
        Pair<V, K> pn = (Pair<V, K>) obj;
        return pn.first.equals(first) && pn.second.equals(second);
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }
}