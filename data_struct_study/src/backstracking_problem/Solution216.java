package backstracking_problem;

import java.util.ArrayList;
import java.util.List;

public class Solution216 {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempCombination = new ArrayList<>();
        backtracking(k, n, 1, tempCombination, combinations);

        return combinations;
    }

    private void backtracking(int k, int n, int start, List<Integer> tempCombination, List<List<Integer>> combinations) {

        if (k == 0 && n == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }

        if (k == 0 || n == 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            tempCombination.add(i);
            backtracking(k - 1, n - i, i + 1, tempCombination, combinations);
            tempCombination.remove(tempCombination.size() - 1);
        }
    }

}
