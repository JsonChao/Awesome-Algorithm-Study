package greedy_problem;


import java.util.Arrays;

/**
 * 先尝试将最大的饼干分配给最贪心的小朋友
 * O(nlogn)
 * O(1)
 */
public class Solution455 {

    public int findContentChildren(int[] g, int[] s) {

        // 1、排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 2、先尝试将最大的饼干分配给最贪心的小朋友
        int gi = g.length - 1;
        int si = s.length - 1;
        int res = 0;
        while (gi >= 0 && si >= 0) {
            if (s[si] >= g[gi]) {
                res++;
                si--;
            }
            gi--;
        }
        return res;
    }

}
