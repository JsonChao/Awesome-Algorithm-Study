package hash_table_problem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目描述：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *      1、不同的三元组？
 *      2、如果有多个解，解的顺序？
 *      3、如果没有解。
 *
 * 三数之和：需要尤其注意去重，有些繁琐。
 */
public class Solution15 {

    // 排序 + 双指针：时间复杂度O(n ^ 2)，空间复杂度O(n)
    public List<List<Integer>> threeSum(int[] nums) {

        // 1、创建一个返回链表 & 排序nums数组
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);

        // 2、遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 1）、初始化左右指针l、r
            int l = i + 1, r = nums.length - 1;

            // 2）、异常处理：第一个数组大于0，后面的数肯定比它大，肯定不成立了
            if (nums[i] > 0) {
                break;
            }

            // 3、去重1：如果不是第一个数，需要判断和上次枚举的数不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 4、创建一个用于计算三数之和的变量sum, 当左右指针没相遇时才进行处理
            int sum = -1;
            while (l < r){
                // 1）、计算sum
                // [0, 1, 9]
                sum = nums[i] + nums[l] + nums[r];
                // 2）、如果sum大于0，则减小右指针，小于0则增加左指针
                if(sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    // 3）、当sum等于0时，将当前三元组添加到返回链表，
                    // 去重2：当左指针小于右指针时，左右指针需要判断和下次枚举的数不同，
                    // 最后再更新左右指针
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }

                    l++;
                    r--;
                }
            }
        }
        return list;
    }

}
