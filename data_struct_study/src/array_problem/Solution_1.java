package array_problem;

/**
 * 数组中只出现一次的数字：一个整型数组里除了两个数字之外，
 * 其他的数字都出现了两次，找出这两个数。
 *
 * 两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有
 * 元素异或得到的结果为不存在重复的两个元素异或的结果。
 *
 * diff &= -diff 得到出 diff 最右侧不为 0 的位，也就是不存在
 * 重复的两个元素在位级表示上最右侧不同的那一位，利用这一位就可以
 * 将两个元素区分开来。
 */
public class Solution_1 {

    public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        diff &= -diff;
        for (int num : nums) {
            if ((num & diff) == 0)
                num1[0] ^= num;
            else
                num2[0] ^= num;
        }
    }
}
