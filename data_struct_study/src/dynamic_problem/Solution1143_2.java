package dynamic_problem;

// DP：躲避边界条件
public class Solution1143_2 {

    public int longestCommonSubsequence(String s1, String s2) {

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal argument!");
        }

        int m = s1.length();
        int n = s2.length();

        int[][] memo = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }

        return memo[m][n];
    }

}