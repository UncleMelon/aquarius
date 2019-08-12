package jianzhi;

import java.util.Arrays;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 前缀表达式对应于二叉树的先序遍历，先访问根，再访问左子树，然后访问右子树；
 * 中缀表达式对应于二叉树的中序遍历，先访问左子树，再访问根，然后访问右子树；
 * 后缀表达式对应于二叉树的后序遍历，先访问左子树，再访问右子树，然后访问根；
 * @author matthew_wu
 * @since 2019-08-02 11:24
 */
public class Exercise6 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        if (pre.length == 0 || in.length == 0) {
            return null;
        }

        if (pre.length != in.length) {
            return null;
        }

        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}
