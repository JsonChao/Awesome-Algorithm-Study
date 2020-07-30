package hash_table_problem;


import java.util.HashMap;
import java.util.HashSet;

/**
 * 219、217（简化版219）：
 *      1、暴力枚举法：O(n^2)
 *      2、滑动窗口 + 查找表：O(n) 空间 O(k)
 *
 * O(n)
 * O(k)
 */
public class Solution219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        if (nums == null || nums.length <= 1) {
            return false;
        }

        if (k <= 0) {
            return false;
        }

        HashSet<Integer> record = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 1、在滑动窗口中只要有相同元素就是 true
            if (record.contains(nums[i])) {
                return true;
            }

            record.add(nums[i]);
            // 2、维护滑动窗口的大小：移除滑动窗口最左边的元素
            if (record.size() == k + 1) {
                record.remove(nums[i - k]);
            }
        }

        return false;
    }

}
