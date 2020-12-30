package LinkedList_problem;


/**
 * 1、pre、cur、next + 循环：
 *
 * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
 * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。在更改引用之前，还
 * 需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution206 {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 1、pre、cur、next + 循环
    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }

}