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

        ArrayList<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    private void inorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            inorderTraversal(node.left, res);
            res.add(node.val);
            inorderTraversal(node.right, res);
        }
    }


}