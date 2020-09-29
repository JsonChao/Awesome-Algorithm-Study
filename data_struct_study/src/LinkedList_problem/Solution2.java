package LinkedList_problem;


/**
 * 2：
 *      1、数字之外是否有前置的0。（除0以外，没有前置0）
 *      2、负数（不是）。
 *
 *  prev ListNode
 */
public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev=new ListNode(0), l=prev;
        int carry=0;
        while(l1!=null||l2!=null){
            int x=l1==null?0:l1.val;
            int y=l2==null?0:l2.val;
            int sum=x+y+carry;
            carry=sum/10;
            prev.next=new ListNode(sum%10);
            prev=prev.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        if (carry>0) prev.next=new ListNode(carry);
        return l.next;
    }
}
