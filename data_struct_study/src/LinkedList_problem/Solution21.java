package LinkedList_problem;


/**
 * 合并两个有序链表为一个有序链表：设立链表的虚拟头结点
 */
public class Solution21 {

    // 时间复杂度：O(n+m)，函数 mergeTwoList 至多只会递归调用每个节点一次。
    // 因此，时间复杂度取决于合并后的链表长度。
    // 空间复杂度：O(n+m)，递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小
    // 取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 1、当l1或l2为空时的直接返回另一个链表
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 2、判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定
        // 下一个添加到结果里的节点。如果两个链表有一个为空，递归结束
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

}
