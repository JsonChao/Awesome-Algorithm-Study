package dynamic_problem;

import java.util.Arrays;

/**
 * 记忆化搜索：优化状态转移
 * O(n)
 * O(n)
 */
public class Solution198_5 {

    private int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int index) {

        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        // 放弃当前房子抢劫下一个房子 或 抢劫当前房子考虑下下个房子
        return memo[index] =
                Math.max(tryRob(nums, index + 1),
                nums[index] + tryRob(nums, index + 2));
    }

}