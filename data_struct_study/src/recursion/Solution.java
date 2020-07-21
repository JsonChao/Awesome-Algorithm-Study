package recursion;


/**
 *  Leetcode 203: Remove Linked List Elements
 *      删除多个节点时使用 while 循环。
 *      1、Solution：注意内存释放 + 不使用虚拟头结点。
 *      2、Solution2：不使用虚拟头结点。
 *      3、Solution3：使用虚拟头结点。
 */
class Solution {

    public ListNode removeElements(ListNode head, int val) {

        // 1、由于没有使用虚拟头结点，要删除的节点时头结点时需要特殊处理。
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode = null;
        }

        // 2、如果 head 为 null，说明已经删除完毕，直接返回 null 即可。
        if (head == null) {
            return null;
        }

        // 3、从中间开始才有需要删除的节点。
        // 重要细节：需要使用一个新变量记录待删除节点的前一个节点
        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                ListNode delNode = pre.next;
                pre.next = delNode.next;
                delNode = null;
            } else {
                pre = pre.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }
}
