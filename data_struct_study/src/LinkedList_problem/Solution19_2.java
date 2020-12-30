package LinkedList_problem;

/**
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *
 * O(n)
 * O(1)
 */
public class Solution19_2 {

    // 2、双指针：设立两个指针 p、q，他们的距离为n，当 q 遍历到 null 时，p.next 即为 delNode
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 1、设立虚拟头结点，便于后续循环的计算
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 2、创建双指针p、q
        ListNode p = dummyHead;
        ListNode q = dummyHead;

        // 3、先移动q：为后续获取待删除的前一个节点，需执行 n + 1 次遍历，而不是 n 次
        for (int i = 0; i < n + 1; i++) {
            assert q != null;
            q = q.next;
        }

        // 4、再同时移动q、p：当 q == null 时，p 即为待删除的前一个节点
        while (q != null) {
            q = q.next;
            p = p.next;
        }

        // 5、将待删除的前一个节点的指针指向后一个节点
        p.next = p.next.next;

        return dummyHead.next;
    }

}

