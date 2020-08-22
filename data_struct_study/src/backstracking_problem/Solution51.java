package backstracking_problem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯法是经典人工智能的基础
 *      1、剪枝
 *      2、快速判断不合法的情况：
 *          竖向：col[i] 表示第i列被占用
 *          对角线1：dia1[i] 表示第i对角线1被占用—— 2*n-1个 i+j。对角线相加
 *          对角线2：dia2[i] 表示第i对角线2被占用—— 2*n-1个 i-j+n-1。对角线相减
 * O(n * n)
 * O(n)
 */
public class Solution51 {

    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {

        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        res = new ArrayList<>();

        LinkedList<Integer> row = new LinkedList<>();
        putQueue(n, 0, row);
        return res;
    }

    // 摆放第 index 行的皇后
    private void putQueue(int n, int index, LinkedList<Integer> row) {

        if (index == n) {
            res.add(generateBroad(n, row));
            return;
        }

        // 尝试将 index 行的皇后摆放在第 i 列
        for (int i = 0; i < n; i++) {
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                row.addLast(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueue(n , index + 1, row);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.removeLast();
            }
        }
    }

    private List<String> generateBroad(int n, LinkedList<Integer> row) {

        assert row.size() == n;

        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[row.get(i)] = 'Q';
            board.add(new String(charArray));
        }
        return board;
    }

}