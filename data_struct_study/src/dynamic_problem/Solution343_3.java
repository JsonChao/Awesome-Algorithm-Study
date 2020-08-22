package dynamic_problem;

import array.Array;

import java.util.Arrays;

/**
 * dp + 记忆化搜索
 * O(n ^ 2)
 * O(n)
 */
public class Solution343_3 {

    private int[] memo;

    public int integerBreak(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("n is illegal argument!");
        }

        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解 memo[i]
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

}