package dynamic_problem;

public class Solution213 {

    public int rob(int[] nums) {


        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        return Math.max(tryRob(nums, 0, n - 2), tryRob(nums, 1, n - 1));
    }

    private int tryRob(int[] nums, int first, int last) {

        int pre2 = 0, pre1 = 0;
        for (int i = first; i <= last; i++) {
            int cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

}
