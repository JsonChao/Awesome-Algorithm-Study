package dynamic_problem;

import java.util.Arrays;


/**
 * 题目描述：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的
 * 情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 1、记忆化数组 + 递归（自上而下）
 * 时间复杂度：O((len(s1) * len(s2))
 * 空间复杂度：O((len(s1) * len(s2))
 */
public class Solution1143 {

    private int[][] memo;

    // 1、记忆化数组 + 递归（自上而下）
    public int longestCommonSubsequence(String s1, String s2) {
        // 1、如果s1或s2等于null，则抛出异常，等于0则直接返回0
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal arguments");
        }

        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        // 2、创建记忆数组并初始化第一维数组下标都为-1
        memo = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 3、递归计算 [0...m] 与 [0...n] 的最长公共子序列的长度
        return lcs(s1, s2, n1 - 1, n2 - 1);
    }

    private int lcs(String s1, String s2, int m, int n) {
        // 1）、如果m或n小于0，则返回0
        if (m < 0 || n < 0) {
            return 0;
        }

        // 2）、如果memo当前位置不等于-1表明已计算过，则直接返回
        if (memo[m][n] != - 1) {
            return memo[m][n];
        }

        // 3）、创建返回变量res，如果s1的m位置字符等于s2的n位置字符，
        // 则res加1并继续递归计算m与n低一层级的lcs，
        // 否则，取m低一层级与n低一层级中递归lcs的最大值
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