package LinkedList_problem;

/**
 * 两个链表的公共节点
 *
 * 两个链表长度分别为L1+C、L2+C， C为公共部分的长度，第一个人走了L1+C步后，
 * 回到第二个人起点走L2步；第2个人走了L2+C步后，回到第一个人起点走L1步。
 * 当两个人走的步数都为L1+L2+C时这两个家伙就相爱了。
 *
 * 时间复杂度：O(L1+L2+C)
 * 空间复杂度：O(1)
 */
public class Solution_1 {

    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 1、定义双指针
        ListNode l1 = pHead1, l2 = pHead2;

        // 2、当l1和l2没有相遇时：同时移动l1和l2，当有一方到达尾部时，
        // 就会从另一端开始，当两者相遇时，此时的位置就是相交的位置了
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }

        return l1;
    }

}
