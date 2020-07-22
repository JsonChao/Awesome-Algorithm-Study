package array_problem;

/**
 * O(n)
 * O(1)
 */
public class Solution283_2 {

    public void moveZeroes(int[] nums) {

        // [0，k)中的元素都用来保存原地的非零元素
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }

        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
