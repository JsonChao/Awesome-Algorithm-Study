package dynamic_problem;

/**
 * 目标和：一个朴素的想法是用【暴力破解】 ，枚举所有的正负组合方式，
 * 对每一次结果进行筛选，但是这样就超时了。这样的暴力破解法也可以
 * 写成【递归】 的形式（会导致一部分不可避免的重复运算）。
 * 但其实呢，我们可以把所有的数分为两部分，第一部分用加号，
 * 第二部分用减号，由此可以得出用加号部分的和。问题就被转
 * 化成了 416. 分割等和子集 ，即寻找子集使得和为某一特定
 * 数字，每一个数字仅能用1次。
 */
public class Solution494 {

    public int findTargetSumWays(int[] nums, int S) {

        // 1、计算数组和
        int sum = countSum(nums);

        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }

        int W = (sum + S) / 2;
        int[] dp = new int[W + 1];
        dp[0] = 1;
        for (int num:nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] +  dp[i - num];
            }
        }

        return dp[W];
    }

    private int countSum(int[] nums) {
        int res = 0;
        for (int i:nums) {
            res += i;
        }
        return res;
    }

}


