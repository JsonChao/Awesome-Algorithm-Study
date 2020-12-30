package queue_problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目描述：给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 一、BFS
 * 思路： 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
 * 时间复杂度： O(N)，每个节点都入队出队了 1 次。
 * 空间复杂度： O(N)，使用了额外的队列空间。
 *
 * 二、DFS （时间100%）
 * 思路： 我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，
 * 就可以保证每层都是最先访问最右边的节点的。
 *
 * （与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序
 * 遍历每层最先访问的是最左边的节点）
 *
 * 时间复杂度： O(N)，每个节点都访问了 1 次。
 * 空间复杂度： O(N)，因为这不是一棵平衡二叉树，二叉树的深度
 * 最少是logN, 最坏的情况下会退化成一条链表，深度就是 N，
 * 因此递归时使用的栈空间是 O(N) 的。
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

    // 1、BFS: 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
    // 时间复杂度： O(N)，每个节点都入队出队了 1 次。
    // 空间复杂度： O(N)，使用了额外的队列空间。
    public List<Integer> rightSideView(TreeNode root) {
        // 1、创建一个列表用于保存返回值，如果root等于null，则返回
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用队列先进先出的特性：创建一个队列（入队出队每一层的所有节点），并将根节点入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 3、当队列不为空时
        while (!queue.isEmpty()) {

            // 1）、遍历当前层次对应的队列，如果当前节点有左右节点则入队，如果遍历的当前节点
            // 是当前层的最后一个节点则放入结果列表
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (i == size - 1) {
                    // 只将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    // 1、创建一个全局返回列表，便于操作
    List<Integer> res = new ArrayList<>();

    // 2、DFS：我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，
    // 就可以保证每层都是最先访问最右边的节点的。
    // 时间复杂度： O(N)，每个节点都访问了 1 次。
    // 空间复杂度： O(N)，因为这不是一棵平衡二叉树，二叉树的深度
    // 最少是logN, 最坏的情况下会退化成一条链表，深度就是 N，
    // 因此递归时使用的栈空间是 O(N) 的。
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        // 1）、异常处理，如果根节点为空，直接返回
        if (root == null) {
            return;
        }

        // 2）、记录下当前层的访问节点
        if (depth == res.size()) {
            res.add(root.val);
        }

        // 3）、深度+1，保证每一层只记录一个节点
        depth++;

        // 4）、保证最先访问右子节点
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

}
