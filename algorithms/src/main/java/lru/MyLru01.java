package lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 头插法的代码实现和尾插法基本一致，只是afterEntryInsertion()和afterEntryAccess()方法有所改动
 * @author matthew_wu
 * @since 2019-08-07 11:18
 */
public class MyLru01<K, V> {

    private int maxSize;
    private Map<K, Entry<K, V>> map;
    private Entry head;
    private Entry tail;

    public MyLru01(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>();
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>();
        entry.key = key;
        entry.value = value;
        afterEntryInsertion(entry);
        map.put(key, entry);

        if (map.size() > maxSize) {
            map.remove(head.key);
            afterEntryRemoval(head);
        }
    }

    private void afterEntryInsertion(Entry<K, V> entry) {
        if (entry != null) {
            // 第一个元素
            if (head == null) {
                head = entry;
                tail = head;
                return;
            }

            // 不是第一个元素
            if (tail != entry) {
                Entry<K, V> pred = tail;
                entry.before = pred;
                tail = entry;
                pred.after = entry;
            }
        }
    }

    private void afterEntryAccess(Entry<K, V> entry) {
        Entry<K, V> last;

        if ((last = tail) != entry) {
            Entry<K, V> p = entry, b = p.before, a = p.after;
            p.before = p.after = null;

            // entry是第一个元素
            if (b == null) {
                head = a;
            } else {
                b.after = a;
            }

            // entry是最后一个元素
            if (a == null) {
                last = b;
            } else {
                a.before = b;
            }

            //将当前元素插入到链表尾部
            if (last == null) {
                head = p;
            } else {
                p.before = last;
                last.after = p;
            }
            tail = p;
        }

    }

    private Entry<K, V> getEntry(K key) {
        return map.get(key);
    }

    private V get(K key) {
        Entry<K, V> entry = this.getEntry(key);

        if (entry == null) {
            return null;
        }
        afterEntryAccess(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry<K, V> entry = this.getEntry(key);
        afterEntryRemoval(entry);
    }


    private void afterEntryRemoval(Entry<K, V> entry) {
        if (entry != null) {
            Entry<K, V> p = entry, b = p.before, a = p.after;
            p.before = p.after = null;

            if (b == null) {
                head = a;
            } else {
                b.after = a;
            }

            if (a == null) {
                tail = b;
            } else {
                a.before = b;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Entry<K, V> entry = head;

        while (entry != null) {
            sb.append(String.format("%s:%s", entry.key, entry.value));
            sb.append(" ");
            entry = entry.after;
        }
        return sb.toString();
    }

    static final class Entry<K, V> {
        private Entry before;
        private Entry after;
        private K key;
        private V value;
    }

    public static void main(String[] args) {
        MyLru01<String, String> map = new MyLru01<>(5);
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
几点linkedhashmap的底层原理:
    1 linkedhashmap在hashmap的数组加链表结构的基础上，将所有节点连成了一个双向链表。
    2 当主动传入的accessOrder参数为false时, 不支持get方法更新链表的。
    3 当主动传入的accessOrder参数为true时，使用put方法新加入的元素，如果遇到了哈希冲突，并且对key值相同的元素进行了替换，
    就会被放在双向链表的尾部，当元素超过上限且removeEldestEntry方法返回true时，直接删除最早元素以便新元素插入。如果没有冲突直接放入，
    同样加入到链表尾部。使用get方法时会把get到的元素放入双向链表尾部。
    4 linkedhashmap的扩容比hashmap来的方便，因为hashmap需要将原来的每个链表的元素分别在新数组进行反向插入链化，
    而linkedhashmap的元素都连在一个链表上，可以直接迭代然后插入。
    5 linkedhashmap的removeEldestEntry方法默认返回false，要实现lru很重要的一点就是集合满时要将最久未访问的元素删除，
    在linkedhashmap中这个元素就是头指针指向的元素。实现LRU可以直接实现继承linkedhashmap并重写removeEldestEntry方法来设置缓存大小。
    jdk中实现了LRUCache也可以直接使用。

    顺序分为访问顺序和插入顺序，acessOrder为true时维护访问顺序，为false时维护插入顺序，不论true和false，
    put进来的元素都会加入双向链表。加入双向链表的操作是在每次newNode时执行的。
*/

