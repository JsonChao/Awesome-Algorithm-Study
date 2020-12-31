package binary_search_tree_problem;


/**
 * 二叉树根节点到所有叶子节点的路径之和：
 * 先序遍历的思想(根左右)+数字求和(每一层都比上层和*10+当前根节点的值)
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
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
        // 1、创建一个路径和变量sum，如果root为null，则返回0，最后按照前序遍历的思想计算路径和
        int sum = 0;
        if (root == null) {
            return sum;
        }
        return preOrderSumNumbers(root, sum);
    }

    public int preOrderSumNumbers(TreeNode root, int sum) {
        // 1）、如果root节点为null则返回路径0
        if (root == null) {
            return 0;
        }

        // 2）、计算路径和：每一层都是上一层 * 10 + 当前节点值
        sum = sum * 10 + root.val;

        // 3）、如果遍历到左右子节点都为null时，则说明已经是叶子节点，返回sum即可
        if (root.left == null && root.right == null) {
            return sum;
        }

        // 4、使用先序遍历的思想去递归计算总的路径和
        return preOrderSumNumbers(root.left, sum) + preOrderSumNumbers(root.right, sum);
    }

}
