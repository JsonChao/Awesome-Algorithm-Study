package dynamic_problem;

import java.util.Arrays;

/**
 * 记忆化搜索
 * O(n ^ 2)
 * O(n)
 */
public class Solution198_3 {

    private int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, nums.length - 1);
    }

    private int tryRob(int[] nums, int index) {

        if (index < 0) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        int res = 0;
        for (int i = 0; i <= index; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i - 2));
        }
        memo[index] = res;
        return res;
    }

}