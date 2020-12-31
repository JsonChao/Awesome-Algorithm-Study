package binary_search_tree_problem;

import java.util.*;


/**
 * 题目描述：请实现一个函数按照之字形打印二叉树，即第一行按照
 * 从左到右的顺序打印，第二层按照从右至左的顺序打印，
 * 第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class Solution_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 队列 + 反转变量reverse记录当前行的打印顺序，
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        // 1、创建一个返回链表 & 添加根节点的队列
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);

        // 2、创建一个反转变量记录当前层是否需要反转，默认第一层不需要反转
        // 当队列不为空时
        boolean reverse = false;
        while (!queue.isEmpty()) {
            // 1）、创建一个列表添加当前层次的值
            ArrayList<Integer> list = new ArrayList<>();

            // 2）、当队列数量大于0时，取出队头节点，如果节点不为空，
            // 则添加值到列表中，并且把左右节点添加到队列中
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }

            // 3）、偶数层数，反转变量为true，此时将列表反转，并将reverse变量置反
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;

            // 4）、如果列表大小不等于0，则添加到返回列表中
            if (list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }

}
