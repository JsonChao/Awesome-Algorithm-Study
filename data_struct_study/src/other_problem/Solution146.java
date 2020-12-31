package other_problem;

import java.util.LinkedHashMap;


/**
 * LRU Cache 146：
 *
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 最简单的方式是直接继承LinkedHashMap，并重写getOrDefault、put、removeEldestEntry方法。
 * 时间复杂度：put/get都是O(1)
 * 空间复杂度：O(capacity)
 */
class Solution146 {

    int capacity;
    // 初始化LinkedHashMap作为cache
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public Solution146(int capacity) {
        // 初始化容量
        this.capacity = capacity;
    }

    public int get(int key) {
        // 1、如果cache中不包含key，则返回-1
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 2、否则将 key 变为最近使用，然后再从cache中获取对应值
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        // 1、如果cache中包含key，则覆盖key的值，并将key变为最近使用的
        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
            return;
        }

        // 2、如果cache大小大于等于容量，则移除链表头部那个最近最久未使用的值，
        // 然后再将新值添加到链表尾部
        if (cache.size() >= this.capacity) {
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, val);
    }

    // 将value变为最近使用的：删除 key，重新插入到链表尾部
    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

}
    