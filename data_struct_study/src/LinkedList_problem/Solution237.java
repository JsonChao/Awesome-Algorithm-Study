package LinkedList_problem;


/**
 * O(1)
 * O(1)
 */
public class Solution237 {

    public void deleteNode(ListNode node) {

        if (node == null || node.next == null) {
            throw new IllegalArgumentException("node must not null and node must not tail node!");
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }

}
