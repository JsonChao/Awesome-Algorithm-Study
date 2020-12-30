package stack_problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 模拟系统栈，非递归二叉树的中序遍历
 * O(n)
 * O(h)
 */
public class Solution94_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Command {
        String s;
        TreeNode node;
        public Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        // 1、创建一个返回列表，如果根节点为null，直接返回
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、创建一个Stack<Command>对象模拟系统栈实现非递归二叉树的中序遍历：倒过来看为左根右
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();

            if (command.s.equals("print")) {
                res.add(command.node.val);
            } else if (command.s.equals("go")) {

                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }

                stack.push(new Command("print", command.node));

                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
            }
        }

        return res;
    }

}
