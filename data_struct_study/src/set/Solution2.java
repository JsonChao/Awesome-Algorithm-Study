package set;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Leetcode 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 */
class Solution2 {

    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet<>();
        for (int item:nums1) {
            set.add(item);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int item:nums2) {
            if (set.contains(item)) {
                list.add(item);
                set.remove(item);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
