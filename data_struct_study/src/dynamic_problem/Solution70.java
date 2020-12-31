package dynamic_problem;

import java.util.Arrays;


/**
 * 1、记忆化搜索 + 递归（自上而下）
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution70 {

    private int[] memo;

    // 1、记忆化搜索 + 递归（自上而下）
    // 时间复杂度：O(n)，空间复杂度：O(n)
    public int climbStairs(int n) {
        // 1、创建记忆数组避免重复运算
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        // 2、递归计算
        return calcWays(n);
    }

    private int calcWays(int n) {
        // 1）、如果楼梯为0或1层，则只有1种方式
        if (n == 0 || n == 1) {
            return 1;
        }

        // 2）、如果记忆数组中没有存储当前楼梯爬的方式个数，
        // 则使用动态转移方程计算并保存在记忆数组中
        if (memo[n] == -1) {
            memo[n] = calcWays(n - 1) + calcWays(n - 2);
        }

        return memo[n];
    }

}