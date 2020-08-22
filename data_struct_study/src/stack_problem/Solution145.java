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
        ArrayList<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    private void postorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            postorderTraversal(node.left, res);
            postorderTraversal(node.right, res);
            res.add(node.val);
        }
    }

}
