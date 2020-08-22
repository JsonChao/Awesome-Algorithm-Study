package dynamic_problem;

import binary_search_tree_problem.Solution404;

public class Solution337 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 1、计算第 1、3...层节点的总值
        int val1 = root.val;
        if (root.left != null) {
            val1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1 += rob(root.right.left) + rob(root.right.right);
        }

        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

}