package hash_table_problem;

import java.util.HashMap;

/**
 * O(n)
 * O(n)
 */
public class Solution1_2 {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            record.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (record.containsKey(target - nums[i])) {
                if (record.get(target - nums[i]) != i) {
                    int[] res = {i, record.get(target - nums[i])};
                    return res;
                }
            }
        }

        throw new IllegalArgumentException("no exists this array!");
    }

}