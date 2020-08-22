package queue_problem;


import LinkedList.LinkedList;
import com.sun.tools.javac.util.Pair;

/**
 * 图：无权图的最短路径。279
 *      1、建模成图论问题：从n到0，每个数字表示一个节点，
 *      如果两个数字x到y相差一个完全平方数，则连接一条边。
 *      我们得到了一个无权图。原问题转换为，求这个无权图中
 *      从n到0的最短路径。
 *
 * 暴力求解
 * O(2^n）
 * O(2^n)
 */
public class Solution279 {

    public int numSquares(int n) {

       LinkedList<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
       queue.addLast(new Pair<>(n, 0));
       while (!queue.isEmpty()) {
           Pair<Integer, Integer> pair = queue.removeFirst();

           int sum = pair.fst;
           int step = pair.snd;

           if (sum == 0) {
               return step;
           }

           for (int i = 1; sum - i * i >= 0; i++) {
               queue.addLast(new Pair<>(sum - i * i, step + 1));
           }
       }

       throw new IllegalArgumentException("no this num squares!");
    }

}