package binary_search_tree_problem;

public class Solution108 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        // 1、根节点为中间的值
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // 2、中序遍历思想构造二叉搜索树
        root.left = toBST(nums, start, mid - 1);
        root.right = toBST(nums, mid + 1, end);
        return root;
    }

}