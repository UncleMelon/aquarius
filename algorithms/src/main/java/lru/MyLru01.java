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
