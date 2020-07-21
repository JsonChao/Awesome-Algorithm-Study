package recursion;


/**
 * 理解递归调用过程：
 *
 * 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6 -> NULL
 * Call: remove 6 in 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6 -> NULL
 * --Call: remove 6 in 2 -> 6 -> 3 -> 4 -> 5 -> 6 -> NULL
 * ----Call: remove 6 in 6 -> 3 -> 4 -> 5 -> 6 -> NULL
 * ------Call: remove 6 in 3 -> 4 -> 5 -> 6 -> NULL
 * --------Call: remove 6 in 4 -> 5 -> 6 -> NULL
 * ----------Call: remove 6 in 5 -> 6 -> NULL
 * ------------Call: remove 6 in 6 -> NULL
 * --------------Call: remove 6 in null
 * --------------Return: null
 * ------------After remove 6: null
 * ------------Return: null
 * ----------After remove 6: null
 * ----------Return: 5 -> NULL
 * --------After remove 6: 5 -> NULL
 * --------Return: 4 -> 5 -> NULL
 * ------After remove 6: 4 -> 5 -> NULL
 * ------Return: 3 -> 4 -> 5 -> NULL
 * ----After remove 6: 3 -> 4 -> 5 -> NULL
 * ----Return: 3 -> 4 -> 5 -> NULL
 * --After remove 6: 3 -> 4 -> 5 -> NULL
 * --Return: 2 -> 3 -> 4 -> 5 -> NULL
 * After remove 6: 2 -> 3 -> 4 -> 5 -> NULL
 * Return: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 * 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 */
public class Solution5 {

    public ListNode removeElements(ListNode head, int val, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if(head == null){
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if(head.val == val)
            ret = res;
        else{
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution5()).removeElements(head, 6, 0);
        System.out.println(res);
    }
}
