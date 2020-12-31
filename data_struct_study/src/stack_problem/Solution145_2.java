package stack_problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * O(n)
 * O(h)
 */
public class Solution145_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Command {
        String val;
        TreeNode node;
        public Command(String s, TreeNode node) {
            this.val = s;
            this.node = node;
        }
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        // 1、创建一个返回列表，如果根节点为null，则直接返回
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用栈Stack<Command>模拟系统栈实现二叉树的非递归遍历：倒过来看为左右根，为什么？因为栈是先进后出的
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();

            if (command.val.equals("print")) {
                res.add(command.node.val);
            } else if (command.val.equals("go")) {

                stack.push(new Command("print", command.node));

                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }

                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
            }
        }

        return res;
    }

}

