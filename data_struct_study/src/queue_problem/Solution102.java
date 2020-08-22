package queue_problem;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 树：层序遍历。102、107、103、199
 * O(n)
 * O(n)
 */
public class Solution102 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 使用队列先进先出的特性
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {

            Pair<TreeNode, Integer> pair = queue.removeFirst();
            TreeNode node = pair.fst;
            int level = pair.snd;

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            assert level < res.size();

            res.get(level).add(node.val);
            if (node.left != null) {
                queue.addLast(new Pair<>(node.left, level + 1));
            }
            if (node.right != null) {
                queue.addLast(new Pair<>(node.right, level + 1));
            }
        }

        return res;
    }

}