package LinkedList_problem;


import java.util.Stack;

/**
 * 445：
 *      1、如果不允许修改输入的链表呢？
 *      2、使用辅助的数据结构。
 */
public class Solution445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 1、使用 stack 存储链表数据，以便从后往前进行计算
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);

        // 2、创建头结点和进位值变量
        ListNode head = new ListNode(-1);
        int c = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || c != 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + c;
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
            c = sum / 10;
        }

        return head.next;
    }

    private Stack<Integer> buildStack(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        return stack;
    }

}
