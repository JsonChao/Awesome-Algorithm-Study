package backstracking_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tempSubset = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        // 统计不同大小的子集
        for (int i = 0; i <= nums.length; i++) {
            backTracing(0, i, visited, tempSubset, subsets, nums);
        }

        return subsets;
    }

    private void backTracing(int start, int size, boolean[] visited, List<Integer> tempSubset, List<List<Integer>> subsets, int[] nums) {

        if (tempSubset.size() == size) {
            subsets.add(new ArrayList<>(tempSubset));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 防重处理
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            tempSubset.add(nums[i]);
            backTracing(i + 1, size, visited, tempSubset, subsets, nums);
            tempSubset.remove(tempSubset.size() - 1);
            visited[i] = false;
        }
    }

}
