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

        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

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
