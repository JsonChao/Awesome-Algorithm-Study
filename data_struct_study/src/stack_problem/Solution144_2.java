package stack_problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * O(n)
 * O(h)
 */
public class Solution144_2 {

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

    public List<Integer> preorderTraversal(TreeNode root) {
        // 1、创建一个返回列表，如果根节点为空，则直接返回
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用Stack<Command>对象模拟系统栈实现二叉树的非递归前序遍历：
        // 倒过来看为根左右，为什么？因为栈是先进后出的
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

                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }

                // 注意栈是先进后出的，不同于递归写法，这里顺序需要反过来
                stack.push(new Command("print", command.node));
            }
        }

        return res;
    }

}
