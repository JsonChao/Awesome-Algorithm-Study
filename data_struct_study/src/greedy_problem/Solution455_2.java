package greedy_problem;

import java.util.Arrays;

/**
 * 先尝试满足最不贪心的小朋友
 * O(nlogn)
 * O(1)
 */
public class Solution455_2 {

    public int findContentChildren(int[] g, int[] s) {

        // 1、排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 2、先尝试满足最不贪心的小朋友
        int gi = 0;
        int si = 0;
        int res = 0;
        while (gi < g.length && si < s.length) {
            if (s[si] >= g[gi]) {
                res++;
                gi++;
            }
            si++;
        }

        return res;
    }

}