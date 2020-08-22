package dynamic_problem;

/**
 * DP
 * O(n^2)
 * O(n)
 */
public class Solution198_2 {

    public int rob(int[] nums) {

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int[] memo = new int[len];
        memo[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                nums[i] = Math.max(memo[i], nums[j] +
                        (j + 2 < len ? memo[j + 2] : 0));
            }
        }
        return memo[0];
    }

}
