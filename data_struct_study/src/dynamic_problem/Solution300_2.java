package dynamic_problem;

import java.util.Arrays;


/**
 * 记忆化搜索 + DP
 * 时间复杂度：O(n ^ 2)
 * 空间复杂度：O(n)
 */
public class Solution300_2 {

    public int lengthOfLIS(int[] nums) {
        // 1、异常处理：如果nums为0，则返回0
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // 2、创建记忆数组并都初始化为-1
        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        // 3、双层遍历：如果下标i处的值大于前面j处的值，则更新memo[i]
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        // 4、再遍历一次计算所有下标处的最大LIS
        int res = memo[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, memo[i]);
        }

        return res;
    }

}