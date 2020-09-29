package backstracking_problem;

/**
 * O(m * n)
 * O(m * n)
 * 岛屿数量: 求连通分支个数【dfs】和 【union find】。使用unionfind的原理为：
 * 顶点数-最小生成树连线数=连通分支个数。
 */
public class Solution200 {

    private int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;
    private boolean[][] visited;

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0;
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    // 深度优先遍历当前岛屿占用区域
    private void dfs(char[][] grid, int x, int y) {

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX, newY) && !visited[newX][newY] &&
                    grid[newX][newY] == '1') {
                dfs(grid, newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

}