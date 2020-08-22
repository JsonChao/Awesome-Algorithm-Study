package array_problem;

public class Solution547 {

    private int n;

    public int findCircleNNum(int[][] M) {

        n = M.length;
        boolean[] visited = new boolean[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                max++;
            }
        }

        return max;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        visited[i] = true;
        for (int k = 0; k < n; k++) {
            if (M[i][k] == 1 && !visited[k]) {
                dfs(M, k, visited);
            }
        }
    }

}
