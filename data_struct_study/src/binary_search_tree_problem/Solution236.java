package binary_search_tree_problem;


/**
 * 二叉树的最近公共祖先
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1、如果根节点为null或q或p，则根节点就是最近的公共祖先
        if (root == null || root == p || root == q) {
            return root;
        }

        // 2、往左右子树递归到底得到左右子树节点, 如果左节点为null，则公共祖先为右节点，
        // 左节点不为null，并且右节点为null，则左节点为公共祖先，如果两者都不为null，则
        // 公共祖先为当前的根节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

}