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
public class Solution19_2 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        ListNode q = dummyHead;
        // 1、为后续获取待删除前一个节点，需执行 n + 1 次遍历，而不是 n 次
        for (int i = 0; i < n + 1; i++) {
            assert q != null;
            q = q.next;
        }

        // 2、当 q == null 时，p 即为待删除的前一个节点
        while (q != null) {
            q = q.next;
            p = p.next;
        }

        p.next = p.next.next;

        return dummyHead.next;
    }

}

