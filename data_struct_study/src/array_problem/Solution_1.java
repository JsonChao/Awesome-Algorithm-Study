package array_problem;

/**
 * 数组中只出现一次的数字：一个整型数组里除了两个数字之外，
 * 其他的数字都出现了两次，找出这两个数。
 */
public class Solution_1 {

    // 1、哈希法：遍历数组，用map记录所有元素出现的次数，然后再遍历数组，找出只出现一次的数。
    // 时间复杂度：O(n), 空间复杂度：O(n)
    // 2、位运算：两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有元素异或得到的结果
    // 为不存在重复的两个元素异或的结果。
    // diff &= -diff 得出 diff 最低位的1，也就是不存在重复的两个元素在位级表示上最右侧不同
    // 的那一位，利用这一位就可以将两个元素区分开来。
    // 时间复杂度：O(n), 空间复杂度：O(1)
    public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {

        // 1、利用 0 ^ x = x 和 x ^ y = 1、x ^ x = 0 的性质来遍历数组，
        // 最后得到的值就是两个不同元素异或的值
        int diff = 0;
        for(int num:nums) {
            diff ^= num;
        }

        // 2、利用 x & -x 得到x最低位的1
        diff &= -diff;

        // 3、遍历数组：利用 最低位1 & 当前元素是否为0来分离两个不同的元素
        for(int num:nums) {
            // 注意这里加括号
            if ((diff & num) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 1, 4};
        int[] num1 = new int[]{0};
        int[] num2 = new int[]{0};
        new Solution_1().FindNumsAppearOnce(nums, num1, num2);
    }
}
