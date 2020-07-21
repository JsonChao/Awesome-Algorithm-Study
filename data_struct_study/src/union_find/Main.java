package union_find;


/**
 * 并查集：由孩子指向父亲，通常用来解决连接问题，最适合合并与查询是相互交替动态进行的请求。
 *
 * 应用场景：
 * 1、网络中节点间的连接状态，网络是一个抽象的概念，用户之间也可以形成网络。
 * 2、数学中的集合类实现，求集合中的并集。
 *
 * 连接问题和路径问题 类比为 堆和顺序表，我们只需要判断它是不是连接的或是最大、最小的元素。
 *
 * 同一个集合 id 就属于一个集合，就表示是相连的。
 *
 * Quick Find （使用数组模拟操作并查集的过程）：
 *      find 与 isConnected 时间复杂度 O(1)，unionElements 时间复杂度 O(n)。
 *
 * Quick Union ：
 *      find 与 unionElements 时间复杂度都为 O(h)，h 为树的高度。
 *      集合编号即为根节点的编号。
 *
 */
public class Main {

    public static void main(String[] args) {

    }
}
