package dynamic_problem;

public class Solution91 {

    public int numDecodings(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        // 1、预先处理 dp[0] 与 dp[1]
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            // 2、累加前一个位数计算得到的值
            int one = Integer.parseInt(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }

            if (s.charAt(i - 2) == '0') {
                continue;
            }

            // 3、计算当前位数需累加的值
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}
