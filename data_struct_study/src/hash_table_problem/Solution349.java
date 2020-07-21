package hash_table_problem;


/**
 * 349（set）、350（map）：
 *      1、C++ 中 map find 不到元素时输出 0
 *
 * set 和 map 不同底层实现的区别
 *                  普通数组实现  顺序数组实现  二分搜索树（平衡）   哈希表
 *         插入       O(1)        O(n)        O(logn)         O(1)
 *         查找       O(n)        O(logn)     O(logn)         O(1)
 *         删除       O(n)        O(n)        O(logn)         O(1)
 *
 * 哈希表失去了数据的顺序性，顺序性即为：
 *       1、数据集中的最大值和最小值。
 *       2、某个元素的前驱和后继。
 *       3、某个元素的 floor 和 ceil。
 *       4、某个元素的排位 rank。
 *       5、选择某个排位的元素 select。
 * C++ 中 map 和 set 的底层实现为平衡二叉树，unordered_map 和 unordered_set 的底层实现为哈希表。
 */
public class Solution349 {
}
