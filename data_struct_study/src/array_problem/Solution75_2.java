package array_problem;

/**
 * 三路快排的思想
 * O(n)
 * O(1)
 */
public class Solution75_2 {

    public void sortColors(int[] nums) {

        // 1、[0, zero] 存储0
        int zero = -1;
        // 2、[two, nums.length -1] 存储2
        int two = nums.length;

        // 3、0,2指针往中间靠，最后中间剩下的就是1
        // 注意 i < two，遍历的区间会随着尾部2的增多而减小
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, --two, i);
            } else if (nums[i] == 0) {
                swap(nums, ++zero, i++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
