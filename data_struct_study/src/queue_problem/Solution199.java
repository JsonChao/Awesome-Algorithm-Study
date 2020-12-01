package queue_problem;

import binary_search_tree_problem.Solution_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 一、BFS
 * 思路： 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
 * 时间复杂度： O(N)O(N)，每个节点都入队出队了 1 次。
 * 空间复杂度： O(N)O(N)，使用了额外的队列空间。
 *
 * 二、DFS （时间100%）
 * 思路： 我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，
 * 就可以保证每层都是最先访问最右边的节点的。
 *
 * （与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序
 * 遍历每层最先访问的是最左边的节点）
 *
 * 时间复杂度： O(N)O(N)，每个节点都访问了 1 次。
 * 空间复杂度： O(N)O(N)，因为这不是一棵平衡二叉树，二叉树的深度
 * 最少是logNlogN, 最坏的情况下会退化成一条链表，深度就是 NN，
 * 因此递归时使用的栈空间是 O(N)O(N) 的。
 */
public class Solution199 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {
            // 如果当前节点所在深度还没有出现在res里，说明在该深度
            // 下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

}
