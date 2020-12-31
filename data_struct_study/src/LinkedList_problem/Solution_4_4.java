package LinkedList_problem;


/**
 * 快慢指针:
 * 时间复杂度：O(n), 空间复杂度：O(1)
 */
public class Solution_4_4 {

    // 4、快慢指针解法：先使用快慢指针找到前半个链表的尾节点，然后翻转后半个部分链表，
    // 最后再使用双指针依次比较对应元素，如果不同则返回false，记得返回前需要还原链表
    // 时间复杂度O(n), 空间复杂度O(1)
    public boolean isPail(ListNode head) {
        // 1、如果只有0或1个节点，则说明是回文结构
        if (head == null || head.next == null) {
            return true;
        }

        // 2、通过快慢指针找到前半部分链表的尾节点：若链表有奇数个节点，则中间的节点应该看作是前半部分；
        // 然后反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 3、判断是否回文：当后半部分的指针P2到达末尾时则比较完成
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4、还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
