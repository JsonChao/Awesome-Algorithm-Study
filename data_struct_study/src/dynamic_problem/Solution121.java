package dynamic_problem;


/**
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
 * 设计一个算法来计算你所能获取的最大利润。
 *
 * 1、穷举框架-状态与选择：
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
 *  这里每天都有三种「选择」：买入、卖出、无操作。
 *  「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（0、1）
 * 2、状态转移框架与 base case：
 * 状态转移方程：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *
 * base case：
 * dp[-1][k][0] = dp[i][0][0] = 0
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 */
public class Solution121 {

    // 时间复杂度：O(n), 空间复杂度：O(1)
    public int maxProfit_k_1(int[] prices) {
        // 1、初始化第i天未持有为0，第i天持有为负无穷
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;

        // 2、遍历价格数组
        for (int price : prices) {
            // 1）、当天未持有等于 前一天未持有和前天持有+当天价格 中的最大值
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);

            // 2）、当天持有等于 前一天持有和第一天未持有（0）-当前价格 中的最大值
            dp_i_1 = Math.max(dp_i_1, -price);
        }

        // 3、返回最后一天未持有的价格
        return dp_i_0;
    }

}
