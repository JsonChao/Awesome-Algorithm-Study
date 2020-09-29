package other_problem;

public class Solution169_2 {

    // 多数元素：我将这种算法称为 【抱对自杀】，每当一个众数碰到一个非众数就会抱住，
    // 然后双双自杀。如果没有遇到足够的非众数，众数们就会等人到齐了再抱对自杀。
    // 最后留下来的当然是众数。
    public int majorityElement(int[] nums) {

        int cnt = 0, majority = nums[0];
        for (int num:nums) {
            majority = (cnt == 0) ? num : majority;
            cnt = (majority == num) ? cnt + 1 : cnt - 1;
        }

        return majority;
    }

}
