package hash_table_problem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * O(len(nums1) + len(nums2))
 * O(len(nums1))
 */
public class Solution350_2 {

    public int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> record = new HashMap<>();
        for (Integer item:nums1) {
            if (!record.containsKey(item)) {
                record.put(item, 1);
            } else {
                record.put(item, record.get(item) + 1);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Integer item:nums2) {
            if (record.containsKey(item) && record.get(item) > 0) {
                result.add(item);
                record.put(item, record.get(item) - 1);
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