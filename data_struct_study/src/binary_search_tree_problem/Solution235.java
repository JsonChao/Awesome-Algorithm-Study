package binary_search_tree_problem;

/**
 * O(lgn)
 * O(h)
 */
public class Solution235 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p == null || q == null) {
            throw new IllegalArgumentException("q or p is not null!");
        }

        if (root == null) {
            return null;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        assert p.val == root.val || q.val == root.val ||
                (root.val - p.val) * (root.val - q.val) < 0;

        return root;
    }

}
