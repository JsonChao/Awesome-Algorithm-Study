package LinkedList_problem;

import java.util.PriorityQueue;


/**
 * 题目描述：给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Solution23 {

    // 1、优先队列：用容量为K的最小堆优先队列，把链表的头结点都放进去，然后出队当前
    // 优先队列中最小的，挂上链表，然后让 出队的那个节点指向的下一个节点 入队，
    // 如此反复，再出队当前优先队列中最小的，直到优先队列为空。
    // 时间复杂度:O(n*log(k))，n是所有链表中元素的总和，k是链表个数。
    public ListNode mergeKLists(ListNode[] lists) {

        // 1、异常处理：如果链表数组为null或长度为0，则直接返回null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 2、创建一个虚拟头结点与最小堆优先队列（o1.val - o2.val 为升序排序）
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        // 3、遍历链表数组：如果链表不为null，则添加到优先队列中
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        // 4、当优先队列不为空时
        while (!pq.isEmpty()) {
            // 1）、取出队首的最小节点，并将cur节点指向它，然后移动cur节点到下一个位置
            ListNode nextNode = pq.poll();
            cur.next = nextNode;
            cur = cur.next;

            // 2）、如果队首的最小节点的下一个节点不为null，则添加其下一节点到优先队列中
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }

        return dummyHead.next;
    }

    // 2、分支法：分而治之，链表两两合并。时间复杂度:O(n*log(k))
    public ListNode mergeKLists2(ListNode[] lists) {
        // 1、异常处理：如果链表数组为null或长度为0，则直接返回null
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {

        // 1、如果合并到左右指针都相等，则直接返回左指针处的链表
        if (left == right) {
            return lists[left];
        }

        // 2、分而治之：最后会递归得到2个单个链表，不断返回两两合并后的链表即可
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

}
