package recursion;

/**
 * 不使用虚拟头结点
 */
public class Solution2 {

    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
//            ListNode delNode = head;
//            head = head.next;
//            delNode = null;
            // 在 LeetCode不需要考虑用于内存释放的第一、三步
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
//                ListNode delNode = pre.next;
//                pre.next = delNode.next;
//                delNode = null;
                // 在 LeetCode不需要考虑用于内存释放的第一、三步
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }

        return head;
    }

}
