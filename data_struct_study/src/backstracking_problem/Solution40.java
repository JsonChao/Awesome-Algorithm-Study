package backstracking_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempCombination = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtracking(tempCombination, combinations, visited, 0, target, candidates);

        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations, boolean[] visited, int start, int target, int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重处理
            if (i != 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                visited[i] = true;
                tempCombination.add(candidates[i]);
                backtracking(tempCombination, combinations, visited, i + 1, target - candidates[i], candidates);
                tempCombination.remove(tempCombination.size() - 1);
                visited[i] = false;
            }
        }
    }

}
