package binary_search_tree_problem;

/**
 * 先序遍历的思想(根左右)+数字求和(每一层都比上层和*10+当前根节点的值)
 */
public class Solution_1 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        return preorderSumNumbers(root, sum);
    }

    public int preorderSumNumbers(TreeNode root, int sum) {
        if (root == null)
            return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return preorderSumNumbers(root.left, sum) + preorderSumNumbers(root.right, sum);
    }
}
