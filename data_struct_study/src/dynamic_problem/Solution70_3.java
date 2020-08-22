package dynamic_problem;

/**
 * Dp => 循环只需记录前两个数的值即可 => 矩阵 or 推导公式 优化
 * 空间 O(n) => 空间 O(1) => 时间 O(logn)
 */
public class Solution70_3 {

    public int climbStairs(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("illegal argument");
        }

        if (n == 1) {
            return 1;
        }

        int pre = 1, cur = 1;
        for (int i = 2; i <= n; i++) {
            int next = pre + cur;
            pre = cur;
            cur = next;
        }

        return cur;
    }

}