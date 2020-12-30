package LinkedList_problem;

/**
 * 判断链表是否有环
 */
public class Solution141 {

    // 双指针：时间复杂度O(n), 空间复杂度O(1)
    public boolean hasCycle(ListNode head) {

        // 1、异常处理：如果头结点为null，则返回false
        // 为什么不判断head.next为null？因为1个节点也可以构成环形链表，即自己指向自己
        if (head == null) {
            return false;
        }

        // 2、创建两个指针节点，l1为head，l2为head的下一个节点
        ListNode l1 = head, l2 = head.next;

        // 3、当l1、l2、l2.next都不为空，则移动两个链表来判断是否有环
        while (l1 != null && l2 != null && l2.next != null) {
            // 1）、两个指针相遇，则说明有环
            if (l1 == l2) {
                return true;
            }

            // 2）、l1指针移动1步，l2指针移动2步
            l1 = l1.next;
            l2 = l2.next.next;
        }

        // 4、如果遍历到链表尾部，则说明没环
        return false;
    }

}
