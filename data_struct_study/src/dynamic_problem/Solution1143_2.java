package dynamic_problem;


/**
 * 2、记忆化数组 + DP（自下而上）：躲避边界条件
 * 时间复杂度：O((len(s1) * len(s2))
 * 空间复杂度：O((len(s1) * len(s2))
 */
public class Solution1143_2 {

    // 2、记忆化数组 + DP（自下而上）：躲避边界条件
    public int longestCommonSubsequence(String s1, String s2) {
        // 1、如果s1或s2等于null，则抛出异常
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal argument!");
        }

        // 2、初始化记忆数组，注意这里的容量为len+1，
        // 为了便于计算，后面的统计位置从下标1开始
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m + 1][n + 1];

        // 3、双层for循环：都从下标1开始，如果s1的i-1位置字符等于s2的j-1位置字符，
        // 则当前位置memo取1+低一层级memo值，
        // 否则，取m低一层级与n低一层级memo中的最大值
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