package list;

/**
 * 在源码中的查找，用到了二分查找，先判断要查找的索引值index,和size比较大小，再判断是从first节点还是last节点开始查找
 * @author matthew_wu
 * @since 2019-08-15 14:53
 */
public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void add(Object value) {
        Node newNode = new Node();
        newNode.value = value;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    private Node getNode(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public Object get(int index) {
        checkElementIndex(index);
        return getNode(index).value;
    }

    public Object remove(int index) {
        checkElementIndex(index);
        Node current;
        if (index == 0) {
            current = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (index == size - 1) {
            current = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            current = getNode(index);
            Node node1 = current.prev;
            Node node3 = current.next;
            node1.next = node3;
            node3.prev = node1;
        }
        size--;
        return current.value;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("out of bounds...");
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("A");
        myLinkedList.add("B");
        myLinkedList.add("C");
        myLinkedList.add("D");
        myLinkedList.remove(2);
        for (int i = 0; i < myLinkedList.getSize(); i++) {
            System.out.println(myLinkedList.get(i));
        }
    }

    static class Node {
        Node prev;
        Node next;
        Object value;
    }
}
