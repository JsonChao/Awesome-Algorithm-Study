package stack_problem;


import java.util.ArrayList;
import java.util.List;

/**
 * 144、94、145：
 *      1、递归实现。
 *      2、循环实现：使用栈模拟系统栈，写出非递归程序。
 * O(n)
 * O(h)
 */
public class Solution144 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        preorderTraversal(root, res);
        return res;
    }

    private void preorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            res.add(node.val);
            preorderTraversal(node.left, res);
            preorderTraversal(node.right, res);
        }
    }
}
