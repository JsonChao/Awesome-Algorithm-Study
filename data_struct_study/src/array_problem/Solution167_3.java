package array_problem;

/**
 * 对撞指针
 * O(n)
 * O(1)
 */
public class Solution167_3 {

    public int[] twoSum(int[] numbers, int target) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("length of number is illegal!");
        }

        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            } else if (numbers[l] + numbers[r] < target) {
                l ++;
            } else {
                r --;
            }
        }

        throw new IllegalArgumentException("no tow sum!");
    }
}
