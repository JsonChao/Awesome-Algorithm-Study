package array_problem;

public class Solution283_4 {

    public void moveZeroes(int[] nums) {

        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {
                    swap(nums, k++, i);
                } else {
                    k++;
                }
            }
        }
    }

    private void swap(int[] nums, int k, int i) {
        int temp = nums[k];
        nums[k] = nums[i];
        nums[i] = temp;
    }
}
