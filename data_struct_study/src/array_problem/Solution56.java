package array_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 首先根据左端点排序，这样只需比较每一次合并后区间的右端点和起始的左端点即可。
 */
public class Solution56 {

    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length==0) return new int[0][];
        List<int[]> list=new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]-o2[0];
            }
        });
        int i=0;
        while (i<intervals.length){
            int l=intervals[i][0], r=intervals[i][1];
            i++;
            while (i<intervals.length&&intervals[i][0]<=r){
                r=Math.max(r,intervals[i][1]); i++;
            }
            list.add(new int[]{l,r});
        }
        return list.toArray(new int[0][]);
    }
}
