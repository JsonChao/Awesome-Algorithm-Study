package backstracking_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 */
public class Solution78 {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tempSubset = new ArrayList<>();
        // 统计不同子集的大小
        for (int i = 0; i <= nums.length; i++) {
           backTracing(0, i, tempSubset, subsets, nums);
        }

        return subsets;
    }

    private void backTracing(int start, int size, List<Integer> tempSubset, List<List<Integer>> subsets, int[] nums) {

        if (tempSubset.size() == size) {
            subsets.add(new ArrayList<>(tempSubset));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            tempSubset.add(nums[i]);
            backTracing(i + 1, size, tempSubset, subsets, nums);
            tempSubset.remove(tempSubset.size() - 1);
        }
    }

}
