package LinkedList_problem;

/**
 * 排序链表：【merge sort + Floyd】
 */
public class Solution148 {

    public ListNode sortList(ListNode head){
        if (head==null||head.next==null) return head;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode curr = slow.next;
        slow.next = null;
        head = sortList(head);
        curr = sortList(curr);

        ListNode pre = new ListNode(0);
        slow = pre;
        while (head!=null&&curr!=null){
            if (head.val<curr.val){
                pre.next = head; pre = pre.next; head = head.next;
            }
            else{
                pre.next = curr; pre = pre.next; curr = curr.next;
            }
        }
        pre.next = head==null? curr:head;
        return slow.next;
    }
}
