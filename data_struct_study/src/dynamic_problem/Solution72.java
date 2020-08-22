package dynamic_problem;


/**
 * 动态规划给出具体解
 *      反向求出具体的解，甚至是所有的解。
 * 编辑距离 72
 */
public class Solution72 {

    public int minDistance(String word1, String word2) {

        // 1、创建 dp 数组并赋值上边界和左边界
        if (word1 == null || word2 == null) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        // 注意
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        // 2、处理编辑距离
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j],
                            dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }

}
