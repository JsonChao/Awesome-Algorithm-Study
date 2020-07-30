package hash_table_problem;

import java.util.TreeSet;

/**
 * 220：
 *      1、滑动窗口 + set.lower_bound，注意使用 long 运算，避免整形溢出。
 *
 * O(nlog(k)
 * O(k)
 */
public class Solution220 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        // 1、异常处理
        if (nums == null || nums.length <= 1) {
            return false;
        }

        if (k <= 0 || t < 0) {
            return false;
        }

        TreeSet<Long> record = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            // 2、treeSet.lower_bound
            if (record.ceiling((long)nums[i] - (long)t) != null &&
                    record.ceiling((long)nums[i] - (long)t) <= ((long)nums[i] + (long)t)) {
                return true;
            }

            // 3、滑动窗口
            record.add((long) nums[i]);
            if (record.size() == k + 1) {
                record.remove((long)nums[i - k]);
            }
        }

        return false;
    }

}
