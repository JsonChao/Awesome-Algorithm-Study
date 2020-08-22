package dynamic_problem;

import java.util.Arrays;

/**
 * DP
 * O((len(s1) * len(s2))
 * O((len(s1) * len(s2))
 */
public class Solution1143 {

    private int[][] memo;

    public int longestCommonSubsequence(String s1, String s2) {

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal arguments");
        }

        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        memo = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            Arrays.fill(memo[i], -1);
        }

        return lcs(s1, s2, n1 - 1, n2 - 1);
    }

    // 统计 [0...m] 与 [0...n] 的最长公共子序列的长度
    private int lcs(String s1, String s2, int m, int n) {

        if (m < 0 || n < 0) {
            return 0;
        }

        if (memo[m][n] != - 1) {
            return memo[m][n];
        }

        int res;
        if (s1.charAt(m) == s2.charAt(n)) {
            res = 1 + lcs(s1, s2, m - 1, n - 1);
        } else {
            res = Math.max(lcs(s1, s2, m - 1, n),
                    lcs(s1, s2, m , n - 1));
        }

        return memo[m][n] = res;
    }

}