package map;

import java.util.ArrayList;
import java.util.TreeMap;


/**
 * Leetcode 350. Intersection of Two Arrays II
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 */
class Solution1 {

    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Integer item:nums1) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (Integer item:nums2) {
            if (map.containsKey(item)) {
                list.add(item);
                map.put(item, map.get(item) - 1);
                if (map.get(item) == 0) {
                    map.remove(item);
                }
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
