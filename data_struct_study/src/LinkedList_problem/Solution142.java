package LinkedList_problem;

/**
 * 环形链表 II：快慢指针+Floyd算法。
 */
public class Solution142 {

    // 1、fast走的步数是slow步数的2倍：f=2s、fast比slow多走了n个环的长度：f=s+nb => s = nb
    // 2、走a+nb步一定是在环的入口
    // 3、slow再走a = 入口 = head走到入口 = a
    // 4、由3得出，起始距离入口 = 第一次相遇位置 + a，所以，此时再次相遇时的节点即为环的入口节点
    public ListNode detectCycle(ListNode head) {

        // 1、创建快慢指针
        ListNode fast = head;
        ListNode slow = head;

        // 2、当fast不为null且fast.next不为null时，则移动fast两步、slow一步，
        // 当fast与slow相遇时，即得到第一次相遇的节点位置
        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        // 3、异常处理：如果fast为null或者fast.next为null，说明没有环，直接返回null
        if (fast == null || fast.next == null) {
            return null;
        }

        // 4、将fast定位到head，再同时移动fast和slow，当第二次相遇时，相遇的节点即为环的入口节点
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

}
