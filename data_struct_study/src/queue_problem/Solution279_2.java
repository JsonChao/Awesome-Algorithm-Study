package queue_problem;

import LinkedList.LinkedList;
import com.sun.tools.javac.util.Pair;

/**
 * 使用 visited 数组，记录每一个入队元素
 * O(n)
 * O(n)
 */
public class Solution279_2 {

    public int numSquares(int n) {

        if (n == 0) {
            return 0;
        }

        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
        queue.addLast(new Pair<>(n, 0));

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {

            Pair<Integer, Integer> pair = queue.removeFirst();
            int sum = pair.fst;
            int step = pair.snd;

            if (sum == 0) {
                return step;
            }

            for (int i = 1; sum - i * i >= 0; i++) {
                int a = sum - i * i;
                if (!visited[a]) {
                    if (a == 0) {
                        return step + 1;
                    }
                    queue.addLast(new Pair<>(a, step + 1));
                    visited[a] = true;
                }
            }
        }

        throw new IllegalArgumentException("no this num squares!");
    }

    public static void main(String[] args) {

        System.out.println((new Solution279_2()).numSquares(12));
        System.out.println((new Solution279_2()).numSquares(13));
    }

}