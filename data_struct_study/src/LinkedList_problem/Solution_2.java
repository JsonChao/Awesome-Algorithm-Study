package LinkedList_problem;

/**
 * 1. 在链表头前边建立哨兵，开始时，已经排好序的链表为空，
 *    因此已经排序的链表的尾部指针指向刚建立的哨兵；
 * 2. 根据选择排序的思想，我们需要每次从未排序的列表中选择最小的一个节点，
 *    利用头插法将其插入到已经排序好的链表的后面；
 * 3. 接着，将已经排序好的链表的尾部指针指向刚插入的元素；
 * 4. 重复步骤2和3，直到所有的元素都已经排好序，即已经排序的链表的尾部指针移动到了链表的尾部。
 */

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution_2 {
    /**
     *
     * @param head ListNode类 the head node
     * @return ListNode类
     */
    public ListNode sortInList (ListNode head) {
        // 寻找最小的元素，并利用头插法插入到节点
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode sorted = dummy;

        while (sorted.next != null) {
            ListNode pre = sorted;
            ListNode cur = sorted.next;
            ListNode pre_min = null;
            ListNode min = null;

            // 寻找最小的数
            while (cur != null) {
                if (min == null || cur.val < min.val) {
                    min = cur;
                    pre_min = pre;
                }
                // 继续向后移动指针
                cur = cur.next;
                pre = pre.next;
            }

            // 利用头插法插入
            pre_min.next = min.next;
            min.next = sorted.next;
            sorted.next = min;

            // 哨兵节点往后移动
            sorted = min;
        }

        return dummy.next;
    }
}
