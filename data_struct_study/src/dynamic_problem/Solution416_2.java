package dynamic_problem;

/**
 * O(n * sum)
 * O(n * sum)
 */
public class Solution416_2 {

    public boolean canPartition(int[] nums) {

        // 1、判断 nums 数组的和是否为偶数
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                throw new IllegalArgumentException("illegal argument!");
            }
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }

        int C = sum / 2;

        // 2、创建 memo 记忆化数组，并同步 nums 数组的第一个元素的状态为 true
        boolean[] memo = new boolean[C + 1];
        for (int i = 0; i <= C; i++) {
            memo[i] = (nums[0] == i);
        }

        // 3、使用背包状态转移方程
        for (int i = 1; i < n; i++) {
            for (int j = C; j >= nums[i]; j--) {
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }

        return memo[C];
    }

}