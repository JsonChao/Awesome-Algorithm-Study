package hash_table_problem;


import java.util.HashSet;
import java.util.Set;

/**
 * 219、217（简化版219）：
 *      1、暴力枚举法：O(n^2)
 *      2、滑动窗口 + 查找表：O(n) 空间 O(k)
 */
public class Solution217 {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num:nums) {
            hashSet.add(num);
        }
        return hashSet.size() < nums.length;
    }

}