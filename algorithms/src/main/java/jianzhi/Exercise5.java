package jianzhi;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * 解决方法 栈或递归
 * @author matthew_wu
 * @since 2019-08-02 10:47
 */
public class Exercise5 {

    //递归
    private static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ints = new ArrayList<>();
        if (listNode == null) {
            return ints;
        }
        findListValue(listNode, ints);
        return ints;
    }

    private static void findListValue(ListNode listNode, ArrayList<Integer> ints) {
        if (listNode.next == null) {
            ints.add(listNode.val);
        } else {
            findListValue(listNode.next, ints);
            ints.add(listNode.val);
        }
    }


    //栈
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> ints = new ArrayList<>();
        if (listNode == null) {
            return ints;
        }
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            ints.add(stack.pop());
        }
        return ints;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(8);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        System.out.println(printListFromTailToHead2(listNode1));
    }

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

