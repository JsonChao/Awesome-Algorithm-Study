package binary_search_tree_problem;


/**
 * 更复杂的递归逻辑
 *      1、node 在路径的情况 && 在 node 的左右子树中去查找它们的和是否为 sum。
 *      2、node 在路径的情况需要处理为负数的情况，并且不一定需要计算到叶子节点。
 * O(n)
 * O(h)
 */
public class Solution437 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 在以root为根节点的二叉树中,寻找和为sum的路径,返回这样的路径个数
    public int pathSum(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }

        return findPath(root, sum) + pathSum(root.left, sum)
                + pathSum(root.right, sum);
    }

    // 在以 root 为根节点的二叉树中，统计和为 sum 的节点的个数
    private int findPath(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }

        int res = 0;
        if (root.val == sum) {
            res += 1;
        }

        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);

        return res;
    }

}
