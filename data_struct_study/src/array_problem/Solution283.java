package array_problem;

import java.util.ArrayList;


/**
 * O(n)
 * O(n)
 */
public class Solution283 {

    public void moveZeroes(int[] nums) {

        ArrayList<Integer> noZeroList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                noZeroList.add(nums[i]);
            }
        }

        for (int i = 0; i < noZeroList.size(); i++) {
            nums[i] = noZeroList.get(i);
        }

        for (int i = noZeroList.size(); i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
