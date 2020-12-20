package hash_table_problem;

import java.util.Arrays;
import java.util.HashMap;

/**
 * O(n)
 * O(n)
 */
public class Solution1_2 {

    // 数组无序时：查找表(map)，将所有元素放入查找表，之后对每一个元素 a，
    // 查找 target - a 是否存在, 时间复杂度O(n)
    public int[] twoSum(int[] arr, int target) {
        // 1、边界异常处理
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("len of arr is illegal");
        }

        // 2、使用map记录每一个元素对应的值与下标
        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < n; i++) {
            record.put(arr[i], i);
        }

        // 3、遍历数组：当map中包含target-nums[i]这个key &
        // 当前这个key的值不是当前元素的下标时说明已找到
        for (int i = 0; i < n; i++) {
            int j = target - arr[i];
            if (record.containsKey(j) && record.get(j) != i) {
                int[] res = {i, record.get(j)};
                return res;
            }
        }

        throw new IllegalArgumentException("no target!");
    }

    public static void main(String[] args) {
        int[] arr = {2, 7 , 11, 15};
        System.out.println(Arrays.toString(new Solution1_2().twoSum(arr, 9)));
    }

}