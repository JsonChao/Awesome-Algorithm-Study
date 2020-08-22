package binary_search_tree_problem;

/**
 * DP：改变状态定义，优化转移方程
 * O(n)
 * O(n)
 */
public class Solution198_8 {

    public int rob(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // 考虑抢劫 [0..n) 房子得到的最大价值
        int[] memo = new int[n];
        memo[0] = nums[0];
        for (int i = 1; i < n; i++) {
            memo[i] = Math.max(memo[i - 1], nums[i] + (i - 2 >= 0 ? memo[i - 2] : 0));
        }
        return memo[n - 1];
    }

}