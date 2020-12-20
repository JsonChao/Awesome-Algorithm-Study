package array_problem;

import java.util.Arrays;

/**
 * 对撞指针
 * O(n)
 * O(1)
 */
public class Solution167_3 {

    // 3、对撞指针：使用左右边界索引，使用这2个索引不断往中间靠拢，时间复杂度O(n), 空间复杂度O(1)
    public int[] twoSum(int[] arr, int target) {
        // 1、异常边界处理
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("len of arr is illegal!");
        }

        // 2、对撞指针
        int l = 0, r = n - 1;
        while (l < r) {
            // 1）、如果两者之和与target相等，则返回
            if (arr[l] + arr[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            } else if (arr[l] + arr[r] < target) {
                // 2）、如果两者之和小于target，则要增大左边界对应的值，即使l+1
                l ++;
            } else {
                // 3）、否则要增大右边界对应的值，即使r-1
                r --;
            }
        }

        throw new IllegalArgumentException("no target!");
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(new Solution167_3().twoSum(arr, 9)));
    }
}
