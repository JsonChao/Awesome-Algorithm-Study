package binary_search_tree_problem;


/**
 * 二叉树的最大深度
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)
 */
public class Solution104 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        // 1、如果根节点为null，则最大深度为0
        if (root == null) {
            return 0;
        }

        // 2、1 + 递归取左右子树深度的最大值
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}