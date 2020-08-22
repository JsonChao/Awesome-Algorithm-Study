package binary_search_tree_problem;

public class Solution230 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int cnt = 0;
    private int val;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return val;
    }

    private void inOrder(TreeNode root, int k) {

        if (root == null) {
            return;
        }
        // 1、遍历到左边，由最小的值开始
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) {
            val = root.val;
            return;
        }
        // 2、然后遍历右边，得到次小的值
        inOrder(root.right, k);
    }

}
