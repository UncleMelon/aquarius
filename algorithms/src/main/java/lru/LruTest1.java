package lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HashMap的查询、插入、修改、删除平均时间复杂度都是O(1)。最坏的情况是所有的key都散列到一个Entry中，时间复杂度会退化成O(N)。
 * 这就是为什么Java8的HashMap引入了红黑树的原因。当Entry中的链表长度超过8，链表会进化成红黑树。
 * 红黑树是一个自平衡二叉查找树，它的查询/插入/修改/删除的平均时间复杂度为O(log(N))。
 *
 * @author matthew_wu
 * @since 2019-08-07 11:04
 */
public class LruTest1 {
    public static void main(String[] args) {
        int size = 5;

        @SuppressWarnings("serial")
        Map<String, String> map = new LinkedHashMap<String, String>(size, .75F, true) {

            @Override
            protected boolean removeEldestEntry(Entry<String, String> eldest) {
                boolean tooBig = size() > size;
                if (tooBig) {
                    System.out.println("最近最少使用的key=" + eldest.getKey());
                }
                return tooBig;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : entrySet()) {
                    sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
                }
                return sb.toString();
            }
        };

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
        System.out.println(map.toString());

        map.put("6", "6");
        map.get("2");
        map.put("7", "7");
        map.get("4");
        System.out.println(map.toString());
    }
}


/*
在JVM中Ehcache的缓存策略包含
    1  LRU - least recently used（最近最少使用）
    2  LFU - least frequently used（最不经常使用）
    3  FIFO - first in first out, the oldest element by creation time（清除最早缓存的数据，不关心是否经常使用）
*/
