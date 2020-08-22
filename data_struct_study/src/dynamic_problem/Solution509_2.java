package dynamic_problem;

import java.util.Arrays;

/**
 * 记忆化搜索 + 递归
 */
public class Solution509_2 {

    private int[] memo;

    public int fib(int n) {

        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return fib(n, memo);
    }

    private int fib(int n, int[] memo) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (memo[n] == -1) {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }

        return memo[n];
    }

}