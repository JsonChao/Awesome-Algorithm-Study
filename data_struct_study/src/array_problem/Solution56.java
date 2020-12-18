package array_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 首先根据左端点排序，这样只需比较每一次合并后区间的右端点和一个区间的左端点即可。
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Solution56 {

    public int[][] merge(int[][] intervals) {

        // 1、异常边界处理
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        // 2、初始化一个记录区间的列表
        List<int[]> list = new ArrayList<>();

        // 3、根据每一个区间的左边界排序
        Arrays.sort(intervals, (o1, o2) -> o1[0]-o2[0]);

        // 4、遍历每一个区间
        int i = 0;
        while (i < intervals.length) {
            // 1）、拿到当前区间的左右边界
            int l = intervals[i][0], r = intervals[i][1];

            // 2）、当下一个区间存在 & 下一个区间的左边界小于当前区间的右边界，
            // 则更新当前区间右边界为 当前区间右边界与下一个区间右边界 两者中的最大值
            i++;
            while (i < intervals.length && intervals[i][0] <= r){
                r  = Math.max(r, intervals[i][1]);
                i++;
            }

            // 3）、添加合并后的区间到列表
            list.add(new int[]{l, r});
        }

        return list.toArray(new int[0][]);
    }


}
