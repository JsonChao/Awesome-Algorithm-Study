package backstracking_problem;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合问题
 * O(n^k)
 * O(k)
 */
public class Solution77 {

    private ArrayList<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {

        res = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) {
            return res;
        }

        LinkedList<Integer> c = new LinkedList<>();
        generateCombination(n, k, 1, c);
        return res;
    }

    private void generateCombination(int n, int k, int start, LinkedList<Integer> c) {

        if (c.size() == k) {
            res.add((List<Integer>) c.clone());
            return;
        }

        for (int i = start; i <= n; i++) {
            c.addLast(i);
            generateCombination(n, k, i + 1, c);
            c.removeLast();
        }
    }

}
