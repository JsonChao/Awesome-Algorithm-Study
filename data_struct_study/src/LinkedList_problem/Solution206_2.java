package LinkedList_problem;

/**
 * （pre、cur、next）
 * O(n)
 * O(1)
 */
public class Solution206_2 {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {

        // 1、递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rhead = reverseList(head.next);

        // 2、从后向前反转链表
        head.next.next = head;
        head.next = null;

        return rhead;
    }

}
