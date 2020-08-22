package dynamic_problem;


/**
 * 发现重叠的子问题
 *      1、暴力解法：回溯遍历将一个树做分割的所有可能性。
 *      2、记忆化搜索
 *      2、动态规划。
 * O(n ^ n)
 * O(n)
 */
public class Solution343 {

    public int integerBreak(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("n is illegal argument");
        }

        return breakInteger(n);
    }

    private int breakInteger(int n) {

        if (n == 1) {
            return 1;
        }

        int res = -1;
        for (int i = 1; i < n; i++) {
            res = max3(res, i * (n - i), i * breakInteger(n - i));
        }
        return res;
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

}