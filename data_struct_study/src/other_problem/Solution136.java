package other_problem;

/**
 * 两个相同的数异或的结果为 0，对所有数进行异或操作，最后的结果就是单独出现的那个数。
 */
public class Solution136 {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num:nums) {
            res = res ^ num;
        }
        return res;
    }

}