package greedy_problem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 设计贪心算法：按照区间的结尾排序，每次选择区间结尾最早的，且和前一个区间不重叠额的区间
 * O(n)
 * O(n)
 */
public class Solution435_2 {

    // Definition for an interval.
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {

        // 1、按照区间的结尾排序
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        // 2、贪心算法：每次选择区间结尾最早的，且和前一个区间不重叠的区间
        int res = 1;
        int pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= intervals[pre].end) {
                res++;
                pre = i;
            }
        }

        return intervals.length - res;
    }

}
