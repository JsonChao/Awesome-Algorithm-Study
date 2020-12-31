package dynamic_problem;


/**
 * 3、Dp + 循环只需记录前两个数的值 => 矩阵快速幂 or 推导公式可以优化时间到 O(logn)
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution70_3 {

    // 3、DP + 循环计算只依赖前两个值即可
    // 时间复杂度：O(n), 空间复杂度：O(1)
    public int climbStairs(int n) {
        // 1、异常处理：n小于0抛出异常
        if (n < 0) {
            throw new IllegalArgumentException("illegal argument");
        }

        // 2、如果爬的楼梯是0或1层，则只有1种方式
        if (n == 0 || n == 1) {
            return 1;
        }

        // 3、DP + 循环计算只依赖前两个值
        int pre = 1, cur = 1;
        int next;
        for (int i = 2; i <= n; i++) {
            next = pre + cur;
            pre = cur;
            cur = next;
        }

        return cur;
    }

}