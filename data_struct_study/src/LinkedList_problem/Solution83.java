package LinkedList_problem;

/**
 * 删除链表中重复的节点
 */
public class Solution83 {

    public ListNode deleteDuplicates(ListNode head) {
        // 1、如果当前节点为null或下一个节点为null时直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 2、递归删除重复节点：如果当前节点与下一个节点值相等，则直接链接下一个节点
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

}
