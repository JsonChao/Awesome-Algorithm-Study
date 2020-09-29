package binary_search_tree_problem;

/**
 * 验证二叉搜索树：【递归】，又用到了树的经典划分：树=根+左子树+右子树。注意
 * 这里左子树和右子树是包含于原树的范围的； 避免 Integer. MIN_VALUE
 * 和 Integer.MAX_VALUE，否则一些特殊的测试用例就会挂掉--这也是我们
 * 使用Integer 而不是int作为上下限变量的原因。
 */
public class Solution98 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root){
        return helper(root,null,null);
    }

    private boolean helper(TreeNode node, Integer a, Integer b){
        if (node == null) return true;

        if(a!=null&&node.val<=a) return false;
        if(b!=null&&node.val>=b) return false;

        if (!helper(node.left,a,node.val)) return false;
        if (!helper(node.right,node.val,b)) return false;

        return true;
    }
}
