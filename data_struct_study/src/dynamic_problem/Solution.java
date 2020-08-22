package dynamic_problem;

/**
 * 背包问题
 * 记忆化搜索 => DP => DP + 滚动数组 => DP + 优化状态转移方程
 * O(n * C)
 * O(C)
 */
public class Solution {

    public int knapsack(int[] w, int[] v, int C){

        if (w == null || v == null || w.length != v.length) {
            throw new IllegalArgumentException("illegal argument!");
        }

        if (C < 0) {
            throw new IllegalArgumentException("C is illegal");
        }

        int n = w.length;
        if (n == 0 || C == 0) {
            return 0;
        }

        int[] memo = new int[C + 1];
        for (int i = 0; i <= C; i++) {
            memo[i] = (i > w[0] ? v[0] : 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = C; j > w[i]; j--) {
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);
            }
        }

        return memo[C];
    }

}
