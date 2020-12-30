package binary_search_tree_problem;


/**
 * 题目描述：给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 二叉搜索树的最近公共祖先
 * 时间复杂度：O(lgn)
 * 空间复杂度：O(n)
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
        // 1、异常处理：如果p或q为null，则抛出异常
        if (p == null || q == null) {
            throw new IllegalArgumentException("q or p is not null!");
        }

        // 2、异常处理：如果根节点为null，则直接返回
        if (root == null) {
            return null;
        }

        // 3、如果p和q都在左边，则需要继续递归左子树查找最近公共祖先
        // 如果p和q都在右边，则需要继续递归右子树查找最近公共祖先
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // 4、异常处理：当p或q为当前根节点或p和q不在同一个子树中才返回当前的最近公共祖先
        assert p.val == root.val || q.val == root.val ||
                (root.val - p.val) * (root.val - q.val) < 0;

        return root;
    }

}
