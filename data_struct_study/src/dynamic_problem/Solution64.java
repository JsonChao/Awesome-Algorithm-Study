package dynamic_problem;

/**
 * 最小路径和：其实到达某点的最小路径，只与该点数值、到达该点左边
 * 的最小路径、到达该点上边的最小路径有关。所以只要找到
 * 【正确循环顺序】，就可以避免所有的中间储存，两层循环即可。
 */
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
