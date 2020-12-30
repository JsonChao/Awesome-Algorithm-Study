package LinkedList_problem;


/**
 * 2、pre、cur、next + 递归
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
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

    // 2、pre、cur、next + 递归
    public ListNode reverseList(ListNode head) {

        // 1、递归终止条件：如果头结点和其下一个节点为null，则直接返回
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);

        // 2、递归到底，从后向前反转链表：5的next指向4，4的next指向null，即断开4的next，便于后续操作
        // 5->4->null => 5->4->3->null => 5->4->3->2->1->null
        head.next.next = head;
        head.next = null;

        return p;
    }

}
