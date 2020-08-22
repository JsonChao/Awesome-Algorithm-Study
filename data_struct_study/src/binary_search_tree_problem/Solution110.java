package binary_search_tree_problem;

public class Solution110 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);

        if (Math.abs(l - r) > 1) {
            result = false;
        }
        return 1 + Math.max(l , r);
    }

}