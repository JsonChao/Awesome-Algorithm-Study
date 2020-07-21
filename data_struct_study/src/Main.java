

/**
 * 数据结构地图：
 *      1、线性结构：动态数组、普通队列、栈、链表、哈希表。
 *      2、树形结构：二分搜索树、AVL 树、红黑树
 *                堆、线段树
 *                多叉树：Trie、并查集
 *      3、图结构：邻接表（与链地址法的哈希表很像，它是一个有 n 个链表的数组，
 *      arr[i] 存储的就是和 i 这个顶点相连接的其它顶点或和 i 这个顶点相连接的边。
 *      邻接矩阵（一个 n * n 的二维数组，G(i,j) 表示从 i 到 j 有一个边）。
 *      4、抽象数据结构：
 *          线性表：动态数组、链表。
 *          栈、队列。
 *          集合、映射：
 *              有序集合，有序映射
 *              无序集合、无序映射
 *
 * 《算法导论》学习：第一、二遍学习时需要忽略其中的数学推导部分。
 *
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] scores = new int[]{88,99,100};
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }

        for (int score:scores){
            System.out.println(score);
        }
    }
}
