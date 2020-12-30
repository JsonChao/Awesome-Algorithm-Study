package queue_problem;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 树：层序遍历。102、107、103、199
 * O(n)
 * O(n)
 * 二叉树的层次遍历：层次遍历和广度优先遍历其实是很像的，
 * 在循环中使用队列就好。唯一的不同是每一层单独一个list，
 * 因此我们就需要想想办法让每层分隔开，我使用的办法就是
 * 每次塞进去一个root作为分隔。
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

    // 层次遍历类似于BFS，在while循环中使用队列 + 每层单独一个list
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1、创建一个双层列表用于保存结果, 如果root为null，则直接返回
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用队列先进先出的特性：创建一个队列, 并首先将根节点入队
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(root, 0));

        // 3、当队列不为空时
        while (!queue.isEmpty()) {
            // 1）、取出队首元素，如果当前元素层次等于res的层次，则说明还有新的子节点要添加，
            // 此时需要添加新的列表到res中
            Pair<TreeNode, Integer> pair = queue.removeFirst();
            TreeNode node = pair.fst;
            int level = pair.snd;

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            // 2）、异常边界处理：确保res的大小大于当前元素的层次
            assert res.size() > level;

            // 3）、添加当前元素节点值到res对应层次中
            res.get(level).add(node.val);

            // 4）、如果当前节点还有左右子节点，则添加到队列尾部
            if (node.left != null) {
                queue.addLast(new Pair<>(node.left, level + 1));
            }

            if (node.right != null) {
                queue.addLast(new Pair<>(node.right, level + 1));
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }

}