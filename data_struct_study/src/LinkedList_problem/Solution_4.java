package LinkedList_problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Java双端队列解法
 */
public class Solution_4 {

    public boolean isPail (ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        while (head != null) {
            deque.addLast(head.val);
            head = head.next;
        }
        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        return true;
    }
}
