package greedy_problem;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法与动态规划的关系 435：
 *      1、最多保留多少个区间。
 *      2、暴力解法：找出所有子区间的组合，之后判断它不重叠。O((2^n)*n)
 *      3、排序后，动态规划——最常上升子序列模型。
 *      4、注意：每次选择中，每个区间的结尾很重要，结尾越小，留给后面的空间越大，所以后面能容纳更多区间。
 *      ——设计出贪心算法：按照区间的结尾排序，每次选择结尾最早的，且和前一个区间不重叠的区间。
 *      5、贪心选择性质的证明：1）、举出反例。2）、反证法：贪心算法为A，最优算法为O，发现A完全能替代O，
 *      且不影响求出最优解。
 * 0(n ^ 2)
 * O(n)
 */
public class Solution435 {

    // Definition for an interval.
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {

        if (intervals.length == 0) {
            return 0;
        }

        // 1、排序
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        // 2、使用 memo[i] 统计以 intervals[i] 为结尾的区间数组的最长不重叠子序列的长度
        int[] memo = new int[intervals.length];
        Arrays.fill(memo, 1);
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[i].start >= intervals[j].end) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        // 3、得到 memo[0...i] 范围中最大的最长不重叠子序列的长度
        int res = 1;
        for (int i = 0; i < intervals.length; i++) {
            res = Math.max(res, memo[i]);
        }

        return intervals.length - res;
    }

}
