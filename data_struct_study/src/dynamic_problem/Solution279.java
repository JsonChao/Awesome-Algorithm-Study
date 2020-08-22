package dynamic_problem;

import java.util.ArrayList;
import java.util.List;

public class Solution279 {

    public int numSquare(int n) {
        List<Integer> squareList = generateSquareList(n);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int square:squareList) {
                if (i >= square) {
                    min = Math.max(min, dp[i - square] + 1);
                }
            }
            dp[i] = min;
        }

        return dp[n];
    }

    private List<Integer> generateSquareList(int n) {

        List<Integer> squareList = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squareList.add(square);
            square += diff;
            diff += 2;
        }
        return squareList;
    }

}
