package LinkedList_problem;


/**
 * （1、遍历存到数组、判断数组。2、双指针)
 * 回文链表: 快慢指针（整除器），把剩下的一半变成逆序，再进行比较。注意奇偶情况讨论。
 */
class Solution234 {
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        ListNode prev=null;
        ListNode slow=head, fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        if (fast!=null) slow=slow.next;
        while(slow!=null){
            ListNode tmp=slow;
            slow=slow.next;
            tmp.next=prev;
            prev=tmp;
        }
        slow=prev;fast=head;
        while(slow!=null){
            if(slow.val==fast.val){slow=slow.next; fast=fast.next;}
            else return false;
        }
        return true;
    }
}

