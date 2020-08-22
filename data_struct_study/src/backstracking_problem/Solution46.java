package backstracking_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列问题
 */
public class Solution46 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {

        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permuteList.add(nums[i]);
                backtracking(permuteList, permutes, visited, nums);
                permuteList.remove(permuteList.size() - 1);
                visited[i] = false;
            }
        }
    }
}
