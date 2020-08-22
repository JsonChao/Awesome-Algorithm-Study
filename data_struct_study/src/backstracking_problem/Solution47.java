package backstracking_problem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排列问题
 */
public class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {

        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            // 去重处理
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
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
