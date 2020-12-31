package LinkedList_problem;

import java.util.Stack;


/**
 * 题目描述：给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * 1、如果不允许修改输入的链表呢？数字之外是否有前置的0？除0以外，没有前置0、负数？不是。
 * 2、使用辅助的数据结构。
 */
public class Solution445 {

    // 本题的主要难点在于链表中数位的顺序与我们做加法的顺序是相反的，
    // 为了逆序处理所有数位，我们可以使用栈：把所有数字压入栈中，
    // 再依次取出相加。计算过程中需要注意进位的情况。
    // 时间复杂度：O(max(m， n)), 其中 m 与 n 分别为两个链表的长度。我们需要遍历每个链表。
    // 空间复杂度：O(m+n), 我们把链表内容放入栈中所用的空间。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 1、使用 stack 存储链表数据，以便从后往前进行计算
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);

        // 2、创建虚拟头结点便于后面计算和进位值变量
        ListNode dummyHead = new ListNode(-1);
        int c = 0;

        // 3、当stack1或stack2或c不为空时
        // 例子：dummyHead->null => dummyHead->7->null => dummyHead->0->7->null => dummyHead->8->0->7->null => dummyHead->7->8->0->7->null
        while (!stack1.isEmpty() || !stack2.isEmpty() || c != 0) {
            // 1、计算当前链表值
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + c;

            // 2、根据当前值创建对应的链表
            ListNode node = new ListNode(sum % 10);

            // 3、将当前节点插入dummyHead后面：当前节点指向dummyHead的下一个节点，
            // 然后重置dummyHead到链表头部位置
            node.next = dummyHead.next;
            dummyHead.next = node;

            // 4、计算进位c
            c = sum / 10;
        }

        return dummyHead.next;
    }

    private Stack<Integer> buildStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        return stack;
    }

}
