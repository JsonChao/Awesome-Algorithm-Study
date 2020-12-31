package dynamic_problem;

import java.util.Arrays;


/**
 * 2、记忆化搜索 + DP（自下而上）
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution70_2 {

    // 2、记忆化搜索 + DP（自下而上）
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public int climbStairs(int n) {
        // 1、创建记忆数组并全部填充为-1
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        // 2、第0或1层的爬楼梯方式都为1种
        memo[0] = 1;
        memo[1] = 1;

        // 3、使用DP自下而上的方式计算目前楼层的爬楼梯方式
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

}