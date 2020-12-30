package queue_problem;


import com.sun.tools.javac.util.Pair;

import java.util.*;

/**
 * 347:
 *      1、最简单的思路：扫描一遍统计概率，排序找到前k个出现频率最高的元素。时间复杂度：O(nlogn)
 *      2、使用优先队列不停地维护我们能找到的前k个出现频率最高的元素。时间复杂度：O(nlogk)
 */
public class Solution347 {

    private class MinComparator implements Comparator<Pair<Integer, Integer>> {

        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            if (!o1.fst.equals(o2.fst)) {
                return o1.fst - o2.fst;
            }
            return o1.snd - o2.snd;
        }
    }

//    public List<Integer> topKFrequent(int[] nums, int k) {
//
//        if (k <= 0) {
//            throw new IllegalArgumentException("illegal argument!");
//        }
//
//        // 1、统计每一个元素出现的频率
//        HashMap<Integer, Integer> freq = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (freq.containsKey(nums[i])) {
//                freq.put(nums[i], freq.get(nums[i] + 1));
//            } else {
//                freq.put(nums[i], 1);
//            }
//        }
//
//        if (k > freq.size()) {
//            throw new IllegalArgumentException("illegal argument!");
//        }
//
//        // 2、使用底层为最小堆的优先队列维护前 k 个频率最大的元素
//        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>(new MinComparator());
//        for (Integer value:freq.keySet()) {
//            int valueFreq = freq.get(value);
//            if (heap.size() == k) {
//                if (heap.peek().fst < valueFreq) {
//                    heap.poll();
//                    heap.add(new Pair<>(valueFreq, value));
//                }
//            } else {
//                heap.add(new Pair<>(valueFreq, value));
//            }
//        }
//
//        ArrayList<Integer> res = new ArrayList<>();
//        while (!heap.isEmpty()) {
//            res.add(heap.poll().snd);
//        }
//
//        return res;
//    }

    // 时间复杂度：O(nlogk), 空间复杂度：O(n)
    public int[] topKFrequent(int[] nums, int k) {

        // 1、将所有 元素:出现频次 添加到 TreeMap 中
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num:nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 2、使用最小堆实现的优先队列维护前 Top k 个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        for (int key:map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        // 3、返回保存 Top k 个元素的数组
        int[] values = new int[k];
        for (int i = values.length - 1; i >= 0; i--) {
            values[i] = pq.remove();
        }
        return values;
    }

}