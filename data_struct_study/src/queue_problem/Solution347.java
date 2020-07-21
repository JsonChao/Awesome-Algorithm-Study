package queue_problem;


/**
 * 347:
 *      1、k 合法性。
 *      2、最简单的思路：扫描一遍统计概率，排序找到前k个出现频率最高的元素。O(nlogn)
 *      3、使用优先队列不停地维护我们能找到的前k个出现频率最高的元素。O(nlogk)
 *      4、维护优先队列，O(nlog(n-k))
 */
public class Solution347 {
}
