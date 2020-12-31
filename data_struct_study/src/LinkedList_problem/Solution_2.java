package LinkedList_problem;


/**
 * 单链表的选择排序：
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class Solution_2 {

    // 根据选择排序的思想寻找最小的元素，并利用头插法插入到节点
    public ListNode sortInList (ListNode head) {
        // 1、创建一个虚拟头结点并将它指向真正的头结点，再赋值给已排序的链表
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;
        ListNode sorted = dummyHead;

        // 2、当已排序链表还有下一个元素时，即移动到链表尾部时已经排好序
        while (sorted.next != null) {
            // 1）、创建pre、cur、pre_min、min便于后续的插入操作
            ListNode pre = sorted;
            ListNode cur = sorted.next;
            ListNode pre_min = null;
            ListNode min = null;

            // 2）、当cur不等于null时：寻找最小的数min与pre_min
            while (cur != null) {
                if (min == null || cur.val < min.val) {
                    min = cur;
                    pre_min = pre;
                }

                // 1）、更新cur和pre：继续向后移动指针
                cur = cur.next;
                pre = pre.next;
            }

            // 3）、将当前min从排序链表中移除，并利用头插法插入
            // 例子：dummyHead->head => dummyHead->min->head => dummyHead->新min->min->head
            pre_min.next = min.next;
            min.next = sorted.next;
            sorted.next = min;

            // 4）、哨兵节点往后移动
            sorted = min;
        }

        return dummyHead.next;
    }

}
