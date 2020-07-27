package hash_table_problem;

import java.util.HashSet;

/**
 * O(len(nums1) + len(nums2))
 * O(len(nums1))
 */
public class Solution_349_2 {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> record = new HashSet<>();
        for (Integer item:nums1) {
            record.add(item);
        }

        HashSet<Integer> result = new HashSet<>();
        for (Integer item:nums2) {
            if (record.contains(item)) {
                result.add(item);
            }
        }

        int[] res = new int[result.size()];
        int i = 0;
        for (Integer item:result) {
            res[i++] = item;
        }

        return res;
    }

}