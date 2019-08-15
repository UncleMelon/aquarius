package hashmap;

/**
 * @author matthew_wu
 * @since 2019-08-13 15:26
 */
public class MyHashMap {

    private Entry[] table;
    private int capacity = 16;
    private float loadfactor = 0.75f;

    private int size;

    public MyHashMap() {
        table = new Entry[capacity];
    }

    public void put(Object key, Object value) {
        Entry toAdd = new Entry(key, value);
        int index = key.hashCode() % table.length;
        Entry entry = table[index];
        if (entry == null) {
            table[index] = toAdd;
            size++;
        } else {
            for (Entry e = entry; e != null; e = e.next) {
                //对于每一个Entry，先判断是不是要覆盖，也要判断是不是到了最后一个节点
                boolean hasValue = (e.key.hashCode() == key.hashCode()) && (e.key == key || e.key.equals(key));
                if (hasValue) {
                    e.value = value;
                    break;
                } else {
                    if (e.next == null) {
                        // 当前是最后一个节点了，
                        // 将要添加的entry放在行首，他的next节点指向老的行首
                        toAdd.next = table[index];
                        table[index] = toAdd;
                        size++;
                    }
                }
            }
        }
        if (size > capacity * loadfactor) {
            // 左移一位，相当与乘以2
            resize(capacity << 1);
        }

    }

    /**
     * do while 先执行一遍循环
     **/
    private void resize(int newCapacity) {
        System.out.println("resize.........");
        Entry[] newTable = new Entry[newCapacity];
        Entry[] src = table;
        for (int i = 0; i < src.length; i++) {
            Entry entry = src[i];
            if (entry != null) {
                Entry e = entry;
                do {
                    // 先保存当前节点的下一个，做为循环判断条件
                    Entry nextEntry = e.next;
                    int index = e.key.hashCode() % newCapacity;
                    e.next = newTable[index];
                    // 针对e.next = newTable[index]这里用到了引用赋值(值传递)
                    // 当为新数组的每一行第一次赋值时，当newTable[index]为null时,将当前entry的next是指向null
                    // 下一行执行newTable[index] = e;并不会更改e.next的值
                    // 当newTable[index]不为null时，当前entry的next指向的是数组index行行首.这样将entry连接起来了
                    // 将当前entry放在数组的index行的行首
                    newTable[index] = e;
                    e = nextEntry;
                } while (e != null);
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    public Object get(Object key) {
        int index = key.hashCode() % table.length;
        Entry entry = table[index];
        for (Entry e = entry; e != null; e = e.next) {
            if (e.key.equals(key) || e.key == key) {
                return e.value;
            }
        }
        return null;
    }

    static class Entry {
        Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        MyHashMap extHashMap = new MyHashMap();
        extHashMap.put("1号", "00");// 0
        extHashMap.put("2号", "00");// 1
        extHashMap.put("3号", "00");// 2
        extHashMap.put("4号", "00");// 3
        extHashMap.put("6号", "6号");// 4
        extHashMap.put("7号", "00");
        extHashMap.put("14号", "00");
        extHashMap.put("22号", "00");
        extHashMap.put("26号", "26号");
        extHashMap.put("27号", "00");
        extHashMap.put("28号", "00");
        extHashMap.put("66号", "00");
        extHashMap.put("26号", "^^^^^");
        extHashMap.print();
        System.out.println("============================");
        extHashMap.put("30号", "00");
        extHashMap.print();
        System.out.println(extHashMap.get("26号"));

    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("下标[" + i + "] ");
            for (Entry e = table[i]; e != null; e = e.next) {
                System.out.print("【key = " + e.key + ",value = " + e.value + "】 ");
            }
            System.out.println();
        }

    }
}
