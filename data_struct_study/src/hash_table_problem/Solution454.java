package hash_table_problem;


import java.util.HashMap;

/**
 * 454：
 *      1、暴力解法：O(n^4)
 *      2、将 D 中的元素放入查找表：O(n^3)
 *      3、将 C + D 的每一种可能放入查找表中：O(n^2)
 *
 * O(n^2)
 * O(n^2)
 */
public class Solution454 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        if (A == null || B == null || C == null || D == null) {
            throw new IllegalArgumentException("array is null!");
        }

        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                if (record.containsKey(sum)) {
                    record.put(sum, record.get(sum) + 1);
                } else {
                    record.put(sum, 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (record.containsKey(-(A[i] + B[j]))) {
                    res += record.get(-(A[i] + B[j]));
                }
            }
        }

        return res;
    }

}