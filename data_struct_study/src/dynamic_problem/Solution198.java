package dynamic_problem;


import java.util.Arrays;

/**
 * 状态的定义和状态转移
 *      1、暴力解法：检查所有房子的组合，对每一个组合，检查是否有相邻的房子，如果没有，记录其价值。找最大值。O((2^n)*n)
 *      2、注意其中对状态的定义：
 *      函数的定义：考虑偷取[x...n-1]范围内的房子。
 *      根据对状态的定义，决定状态转移方程：
 *      f(0) = max{v(0) + f(2), v(1) + f(3) , ... , v(n-3) + f(n-1) ,v(n-2),v(n-1)}
 */
public class Solution198 {

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

        int res = 0;
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = res;
        return res;
    }

}