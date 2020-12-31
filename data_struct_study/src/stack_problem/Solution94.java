package stack_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * O(n)：树中的节点个数。
 * O(h)：树的高度。
 */
public class Solution94 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        // 1、创建一个返回列表
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    // 2、递归实现中序遍历：左根右
    private void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorderTraversal(root.left, res);
            res.add(root.val);
            inorderTraversal(root.right, res);
        }
    }

}