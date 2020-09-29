package dynamic_problem;

import java.util.Arrays;

/**
 * 不同路径：要么【用数学递推公式】，
 *
 * 要么就根据定义【直接计算】。直接计算时需要注意
 *
 * Java “/”的整除性质，溢出，乘除顺序。
 */
public class Solution62 {

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

}
