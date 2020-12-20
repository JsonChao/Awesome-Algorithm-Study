package array_problem;

import java.util.Arrays;

/**
 * 二分查找
 * O(nlogn)
 * O(1)
 */
public class Solution167_2 {

    // 2、循环 + 二分查找：在有序数组中查找 target - arr[i], 时间复杂度O(nlogn)
    public int[] twoSum(int[] arr, int target) {
        // 1、异常边界处理
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("len of arr is illegal!");
        }

        // 2、循环 + 二分查找：在有序数组中查找target-nums[i]
        for (int i = 0; i < n; i++) {
            int j = binarySearch(arr, i + 1, n - 1, target - arr[i]);
            if (j != -1) {
                int[] res = {i + 1, j + 1};
                return res;
            }
        }

        // 3、如果没有找到，则抛出异常
        throw new IllegalArgumentException("no target!");
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        // 1、左右边界异常处理
        int n = arr.length;
        if (l < 0 || l > n - 1) {
            throw new IllegalArgumentException("l is illegal!");
        }

        if (r < 0 || r > n - 1) {
            throw new IllegalArgumentException("r is illegal!");
        }

        // 2、二分搜索
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                return m;
            } else if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(new Solution167().twoSum(arr, 9)));
    }
}
