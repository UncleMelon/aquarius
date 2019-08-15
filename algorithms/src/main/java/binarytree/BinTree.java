package binarytree;

import java.util.LinkedList;

/**
 * 二叉树按层遍历的思路：
 * 将root节点，放入队列中（先进先出），然后取出root节点，检查左节点是否为null,不为null，放入队列中，
 * 检查右节点是否为null,不为null，放入队列中，然后打印root节点的值，再从队列中取出下一节点，循环直到队列为空
 * @author matthew_wu
 * @since 2019-08-12 17:13
 */
public class BinTree {

    public static void main(String[] args) {
        Node  root = new Node("A");
        root.left = new Node("B");
        root.right = new Node("C");
        root.left.left = new Node("D");
        root.left.right = new Node("E");
        root.right.left = new Node("F");
        root.right.right = new Node("G");

        traversal(root);
    }

    public static void traversal(Node root) {
        System.out.println("二叉树层次遍历");
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            Node temp = list.pop();
            if (temp.left != null) {
                list.offer(temp.left);
            }
            if (temp.right != null) {
                list.offer(temp.right);
            }
            System.out.println(temp.value);
        }
    }

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }
}