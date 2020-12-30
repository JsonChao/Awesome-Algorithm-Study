package LinkedList_problem;

import java.util.ArrayList;
import java.util.List;


/**
 * 判断链表是否是一个回文结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution_4_2 {

    // 2、动态数组（ArrayList) + 双指针解法
    public boolean isPail(ListNode head) {
        // 1、创建一个动态数组，并将链表中的节点都放入里面
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        // 2、初始化一头一尾两个双指针，并同时往中间移动进行比较
        int first = 0;
        int last = list.size() - 1;
        while (first < last) {
            if (!list.get(first).equals(list.get(last))) {
                return false;
            }

            first++;
            last--;
        }
        return true;
    }

}
