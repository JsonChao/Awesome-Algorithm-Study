package other_problem;

public class Solution169_2 {

    public int majorityElement(int[] nums) {

        int cnt = 0, majority = nums[0];
        for (int num:nums) {
            majority = (cnt == 0) ? num : majority;
            cnt = (majority == num) ? cnt + 1 : cnt - 1;
        }

        return majority;
    }

}
