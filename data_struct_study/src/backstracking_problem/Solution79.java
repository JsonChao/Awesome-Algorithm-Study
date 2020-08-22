package backstracking_problem;

/**
 * 二维平面上的回溯法
 * O(m*n*m*n)
 * O(m*n)
 */
public class Solution79 {

    private int m;
    private int n;
    private int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {

        if (board == null || word == null) {
            throw new IllegalArgumentException("board and word is null!");
        }

        m = board.length;
        if (m == 0) {
            throw new IllegalArgumentException("board length is illegal argument");
        }

        n = board[0].length;
        if (n == 0) {
            throw new IllegalArgumentException("board length is illegal argument");
        }

        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean searchWord(char[][] board, String word, int index, int startX, int startY) {

        if (index == word.length() - 1) {
            return word.charAt(index) == board[startX][startY];
        }

        if (word.charAt(index) == board[startX][startY]) {
            visited[startX][startY] = true;
            for (int i = 0; i < 4; i++) {
                int newX = startX + d[i][0];
                int newY = startY + d[i][1];
                if (isAccess(newX, newY) && !visited[newX][newY] &&
                            searchWord(board, word, index + 1, newX, newY)) {
                    return true;
                }
            }
            visited[startX][startY] = false;
        }

        return false;
    }

    private boolean isAccess(int newX, int newY) {
        return newX >= 0 && newX < m && newY >= 0 && newY < n;
    }

}