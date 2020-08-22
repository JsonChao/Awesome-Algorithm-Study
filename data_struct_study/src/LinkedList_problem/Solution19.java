package LinkedList_problem;


/**
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *      3、先遍历一遍计算链表长度；再遍历一遍删除倒数第n个节点。
 *      4、设立两个指针 p、q，他们的距离为n，当 q 遍历到 Null 时，p.next 即为 delNode。
 *
 * O(n)
 * O(1)
 */
public class Solution19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 1、计算链表的长度
        int len = 0;
        for (ListNode cur = dummyHead.next; cur != null; cur = cur.next) {
            len++;
        }

        // 2、获取待删除节点的前一个节点
        int k = len - n;
        assert k >= 0;
        ListNode cur = dummyHead;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }

        // 3、删除指定节点
        cur.next = cur.next.next;

        return dummyHead.next;
    }

}