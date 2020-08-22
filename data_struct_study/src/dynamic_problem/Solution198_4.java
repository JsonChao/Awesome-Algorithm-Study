package dynamic_problem;

public class Solution198_4 {

    public int rob(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] memo = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                memo[i] = Math.max(memo[i], (j - 2) >= 0 ? memo[j - 2] : 0);
            }
        }
        return memo[n - 1];
    }

}