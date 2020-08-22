package binary_search_tree_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义递归问题
 * O(n)
 * O(h)
 */
public class Solution257 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<String> binaryTreePaths(TreeNode root) {

        ArrayList<String> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        if (root.left == null && root.right == null) {
            res.add(Integer.toString(root.val));
            return res;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        for (String s:leftPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        List<String> rightPaths = binaryTreePaths(root.right);
        for (String s:rightPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        return res;
    }

}
