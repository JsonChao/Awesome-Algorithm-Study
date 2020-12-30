package LinkedList_problem;


/**
 * K 个一组翻转链表：不仅仅是穿针引线
 *
 * 题目描述：给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
 *
 * 1、链表分为已翻转部分+待翻转部分+未翻转部分
 * 2、每次翻转前，通过 k 次循环来确定翻转链表的范围
 * 3、需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
 * 4、初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
 * 5、经过k次循环，end 到达末尾，记录待翻转链表的后继 next = end.next
 * 6、翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
 * 7、特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
 *
 * 时间复杂度：O(n*k)，最好的情况为O(n)，最差的情况为 O(n^2)
 * 空间复杂度：O(1)，除了几个必须的节点指针外，我们并没有占用其他空间
 */
public class Solution25 {

    // 时间复杂度：O(n*k)，最好情况为O(n), 最差情况为O(n^2)
    // 空间复杂度：O(1)
    public ListNode reverseKGroup(ListNode head, int k) {

        // 1、异常处理：如果头结点为null或下一个节点为null，则直接返回头结点
        if (head == null || head.next == null){
            return head;
        }

        // 2、创建一个虚拟头结点，并将其指向头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 3、初始化pre和end都指向虚拟头结点，
        // pre表示前驱节点：每次待翻转链表的头结点的上一个节点，
        // end指每次待翻转的链表的尾节点
        ListNode pre = dummyHead;
        ListNode end = dummyHead;

        // 4、当待翻转链表的尾节点的下一个节点不为null时
        while (end.next != null) {
            // 1）、必须要循环k次循环找到待翻转链表的结尾，这里每次循环要判断end是否等于空，
            // 因为如果为空，end.next会报空指针异常。
            // 例子：dummyHead->1->2->3->4->5 若k为2，循环2次，end指向2
            for (int i = 0; i < k && end != null; i++){
                end = end.next;
            }

            // 2）、如果end等于null，即表示待翻转链表的节点数小于k，不进行翻转。
            if (end == null){
                break;
            }

            // 3）、创建后继节点next（方便后面链接链表），然后断开链表，
            // 再创建待翻转链表头部节点
            ListNode next = end.next;
            end.next = null;
            ListNode start = pre.next;

            // 4）、翻转链表，pre.next指向翻转后的链表
            // 例子：1->2 变成2->1。 dummyHead->2->1
            pre.next = reverse(start);

            // 5）、翻转后头节点变到最后，通过头结点的next指针把断开的链表重新链接。
            start.next = next;

            // 6）、更新pre和end为下次待翻转链表头结点的上一个节点，即start
            pre = start;
            end = start;
        }

        return dummyHead.next;
    }

    // 链表翻转
    public ListNode reverse(ListNode head) {
        // 1、异常处理：如果头结点为null或下一个节点为null，则直接返回头结点
        if (head == null || head.next == null){
            return head;
        }

        // 2、初始化前一个节点与当前节点，并定义下一个节点
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode;

        // 3、当当前节点不为null时进行链表翻转
        while (curNode != null){
            // 1）、初始化nextNode
            nextNode = curNode.next;

            // 2）、将当前节点的next指针指向前一个节点
            curNode.next = preNode;

            // 3）、将preNode和curNode指针都向后移动
            preNode = curNode;
            curNode = nextNode;
        }

        // 4、否则，当前节点为null，直接返回preNode即可
        return preNode;
    }

}
