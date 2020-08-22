package backstracking_problem;

import java.util.ArrayList;
import java.util.List;

public class Solution39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> combinations = new ArrayList<>();
        backtracking(new ArrayList<>(), combinations, 0, target, candidates);
        return combinations;
    }

    private void backtracking(ArrayList<Integer> tempCombination, List<List<Integer>> combinations, int start, int target, int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                tempCombination.add(candidates[i]);
                backtracking(tempCombination, combinations, i, target - candidates[i], candidates);
                tempCombination.remove(tempCombination.size() - 1);
            }
        }
    }

}
