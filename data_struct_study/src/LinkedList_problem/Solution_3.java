package LinkedList_problem;

/**
 * 题目描述：输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
 * 本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，
 * 从头节点开始，它们的值依次是1、2、3、4、5、6。
 * 这个链表的倒数第3个节点是值为4的节点。
 *
 * 设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，
 * 则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，
 * 可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，
 * 该位置就是倒数第 K 个节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution_3 {

    public ListNode findKthToTail(ListNode head, int k) {
        // 1、异常处理：如果head==null，直接返回
        if (head == null) {
            return null;
        }

        // 2、初始化第一个指针P1，然后移动P1 k步
        ListNode P1 = head;
        while (P1 != null && k-- > 0) {
            P1 = P1.next;
        }

        // 3、异常处理：如果还没走完k步就到链表尾部了，直接返回null
        if (k > 0) {
            return null;
        }

        // 4、初始化第二个指针P2，当P1不等于null时，则同时移动P1、P2，
        // 当P1等于null时，P2就是倒数第k个节点
        ListNode P2 = head;
        while (P1 != null) {
            P1 = P1.next;
            P2 = P2.next;
        }
        return P2;
    }

}
