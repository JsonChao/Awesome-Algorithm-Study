package LinkedList_problem;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * 判断链表是否是一个回文结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution_4 {

    // 1、Java双端队列解法：利用Deque的pollFirst和pollLast方法来比较，
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public boolean isPail(ListNode head) {
        // 1、如果链表节点数为0或1，则返回true
        if (head == null || head.next == null) {
            return true;
        }

        // 2、将链表的节点都放入双端队列中
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            deque.add(cur.val);
            cur = cur.next;
        }

        // 3、当双端队列大于1时：利用Java双端队列的pollFirst和pollLast方法来判断
        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        return true;
    }
}
