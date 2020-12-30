package LinkedList_problem;


/**
 * 判断链表是否是一个回文结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution_4_3 {

    private ListNode frontPointer;

    // 3、递归：currentNode 指针是先到尾节点，由于递归的特性再从后往前进行比较。
    // frontPointer 是递归函数外的指针。若 currentNode.val != frontPointer.val
    // 则返回 false。反之，frontPointer 向前移动并返回 true。
    public boolean isPail(ListNode head) {
        // 初始化frontPointer
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            // 1、递归到底时执行前后指针的判断
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            // 2、更新frontPointer
            frontPointer = frontPointer.next;
        }
        return true;
    }

}
