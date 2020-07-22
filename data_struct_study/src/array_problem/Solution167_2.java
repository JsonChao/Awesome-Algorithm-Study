package array_problem;

/**
 * 二分查找
 * O(nlogn)
 * O(1)
 */
public class Solution167_2 {

    public int[] twoSum(int[] numbers, int target) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("length of number is illegal!");
        }

        for (int i = 0; i < numbers.length; i++) {
            int j = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (j != -1) {
                int[] res = {i + 1, j + 1};
                return res;
            }
        }

        throw new IllegalArgumentException("no two sum!");
    }

    private int binarySearch(int[] numbers, int l, int r, int target) {

        if (l < 0 || l > numbers.length - 1) {
            throw new IllegalArgumentException("l is out of bound~");
        }

        if (r < 0 || r > numbers.length - 1) {
            throw new IllegalArgumentException("r is out of bound~");
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }
}
