package heap_and_priority_queue;

import java.util.PriorityQueue;
import java.util.TreeMap;


/**
 * 1、最简单的思路：扫描一遍统计概率，排序找到前k个出现频率最高的元素。时间复杂度：O(nlogn)
 * 2、使用优先队列不停地维护我们能找到的前k个出现频率最高的元素。时间复杂度：O(nlogk)
 *
 * Java 的 PriorityQueue 内部是最小堆
 * d 叉堆：d-ary heap
 * 常规堆的缺陷：只能看到堆首的元素。
 * 索引堆：保存元素与之对应的索引。
 * 二项堆
 * 斐波那契堆
 * 普通队列、优先队列、广义队列
 * 栈，也可以理解成是一个队列。
 */
public class Solution2 {

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

        // 2、使用优先队列维护前 Top k 个元素
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
