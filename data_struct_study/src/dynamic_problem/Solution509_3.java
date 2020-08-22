package dynamic_problem;

import java.util.Arrays;

/**
 * 动态规划（DP）+ 记忆化搜索
 */
public class Solution509_3 {

    public int fib(int n) {

        if (n == 0) {
            return 0;
        }

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

}