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

    private class Command {
        String val;
        TreeNode node;
        public Command(String val, TreeNode node) {
            this.val = val;
            this.node = node;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {

            Command command = stack.pop();

            if (command.val.equals("print")) {
                res.add(command.node.val);
            } else if (command.val.equals("go")) {

                // 不同于递归写法，栈是先进后出的，所以需要反过来
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

