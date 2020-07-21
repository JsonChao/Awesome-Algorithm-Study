package recursion;

/**
 * 使用虚拟头结点
 */
public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {

        // 给真实的头结点前面添加一个虚拟的头结点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }

        return dummyHead.next;
    }


}
