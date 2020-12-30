package stack_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * O(n)
 * O(h)
 */
public class Solution145 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // 1、创建一个返回列表
        ArrayList<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    // 2、递归实现二叉树的后续遍历：左右根
    private void postorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            postorderTraversal(node.left, res);
            postorderTraversal(node.right, res);
            res.add(node.val);
        }
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        // 1、创建一个返回列表
        List<Integer> res = new ArrayList<>();
        postOrderTraversal(root, res);
        return res;
    }

    // 2、递归实现二叉树的后续遍历：左右根
    private void postOrderTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            postOrderTraversal(root.left, res);
            postOrderTraversal(root.right, res);
            res.add(root.val);
        }
    }

}
