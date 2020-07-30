package hash_table_problem;


import java.util.HashMap;

/**
 * 447：
 *      1、枚举法（暴力法）：O(n^3)
 *      2、查找表，求距离的平方，避免浮点型运算（注意避免整形移除，当然，这个题不需要处理）：O(n^2)，空间 O(n)
 *
 * O(n^2)
 * O(n)
 */
public class Solution447 {

    public int numberOfBoomerangs(int[][] points) {

        int res = 0;
        for (int i = 0; i < points.length; i++) {

            // 1、hashMap 存储 dis:频次
            HashMap<Integer, Integer> record = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                // 2、计算距离时不开根号，避免整形溢出
                Integer dis = dis(points[i], points[j]);
                if (record.containsKey(dis)) {
                    record.put(dis, record.get(dis) + 1);
                } else {
                    record.put(dis, 1);
                }
            }

            // 3、？
            for (Integer dis:record.keySet()) {
                res += record.get(dis) * (record.get(dis) - 1);
            }
        }

        return res;
    }

    private Integer dis(int[] point1, int[] point2) {
        return (point1[0] - point2[0]) * (point1[0] - point2[0]) +
                (point1[1] - point2[1]) * (point1[1] - point2[1]);
    }

}