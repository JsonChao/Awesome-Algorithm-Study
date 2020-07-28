package hash_table_problem;

import java.util.HashMap;

/**
 * O(n^2)
 * O(n^2)
 */
public class Solution454_2 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        if (A == null || B == null || C == null || D == null) {
            throw new IllegalArgumentException("illegal argument!");
        }

        HashMap<Integer, Integer> mapAB = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                if (mapAB.containsKey(sum)) {
                    mapAB.put(sum, mapAB.get(sum) + 1);
                } else {
                    mapAB.put(sum, 1);
                }
            }
        }

        HashMap<Integer, Integer> mapCD = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                if (mapCD.containsKey(sum)) {
                    mapCD.put(sum, mapCD.get(sum) + 1);
                } else {
                    mapCD.put(sum, 1);
                }
            }
        }

        int res = 0;
        for (Integer sumAB:mapAB.keySet()) {
            if (mapCD.containsKey(-sumAB)) {
                res += mapAB.get(sumAB) * mapCD.get(-sumAB);
            }
        }

        return res;
    }

}