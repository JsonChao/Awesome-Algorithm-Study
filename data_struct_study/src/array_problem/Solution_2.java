package array_problem;

/**
 * 多数投票问题，可以利用 Boyer-Moore 投票算法
 * 来解决这个问题，使得时间复杂度为 O(n)。
 *
 * 使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，
 * 令 cnt++，否则令 cnt--。如果前面查找了 i 个元素，且 cnt == 0，
 * 说明前 i 个元素没有 majority，或者有 majority，但是出现的次数
 * 少于 i / 2 ，因为如果多于 i / 2 的话 cnt 就一定不会为 0 。
 * 此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，
 * 因此继续查找就能找出 majority。
 */
public class Solution_2 {

    // 1、哈希表：用hashMap统计所有元素出现的次数，时间复杂度O(n), 空间复杂度O(n)
    // 2、排序：如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，
    // 那么下标为n/2的元素一定是众数。时间复杂度O(nlog(n))，空间复杂度O(logn)(自己实现堆排序O(1))
    // 3、Boyer Moore投票算法：时间复杂度O(n), 空间复杂度O(1)
    public int majorityElement(int[] nums) {

        // 1、初始化出现次数和候选众数
        int count = 0;
        Integer candidate = null;

        // 2、遍历数组
        for (int num : nums) {
            // 3、如果出现次数为0，则更新当前的候选众数
            if (count == 0) {
                candidate = num;
            }
            // 4、然后更新当前候选众数的出现次数：当前元素与候选众数相等，则+1，否则-1
            count += (num == candidate) ? 1 : -1;
        }

        // 5、最后的候选众数就是真正的众数
        // 为什么？如果候选众数不是真正的众数，则真正众数会和其它非候选人一起反对，此时候选众数就会下台（count==0），
        // 如果候选众数是真正的众数，则真正的众数就会支持，其它非获选人会一起反对，此时候选众数最后一定会当选。
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        System.out.println(new Solution_2().majorityElement(nums));
    }

}
