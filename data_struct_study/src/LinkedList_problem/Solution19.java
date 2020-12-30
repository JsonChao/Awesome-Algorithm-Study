package LinkedList_problem;


/**
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *
 * O(n)
 * O(1)
 */
public class Solution19 {

    // 1、先遍历一遍计算链表长度；再遍历一遍得到待删除节点的前一个节点并删除待删除节点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 1、使用虚拟头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 2、先遍历一遍计算链表的长度
        int len = 0;
        for (ListNode cur = dummyHead.next; cur != null; cur = cur.next) {
            len++;
        }

        // 3、获取待删除节点的前一个节点cur
        int k = len - n;
        assert k >= 0;
        ListNode cur = dummyHead;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }

        // 4、删除指定节点：前一个节点指向待删除节点的后一个节点
        cur.next = cur.next.next;

        return dummyHead.next;
    }

}