package dynamic_problem;

public class Solution494_2 {

    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }

    private int findTargetSumWays(int[] nums, int start, int s) {
        if (start == nums.length) {
            return s == 0 ? 1 : 0;
        }

        return findTargetSumWays(nums, start + 1, s + nums[start]) +
                findTargetSumWays(nums, start + 1, s - nums[start]);
    }

}
