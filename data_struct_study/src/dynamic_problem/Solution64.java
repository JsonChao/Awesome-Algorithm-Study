package dynamic_problem;

public class Solution64 {

    public int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    // 只能从上侧走到该位置
                    dp[j] = dp[j];
                } else if (i == 0) {
                    // 只能从左侧走到该位置
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]);
                }
                dp[j] += grid[i][j];
            }
        }

        return dp[n - 1];
    }

}
