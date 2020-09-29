package dynamic_problem;

/**
 * 最大正方形：可以用动态规划，以某点为右下角的最大正方形边长 只与
 * 以该点上面、左边、左上相邻点为右下角的最大正方形边长有关，
 * 取最小+1的关系。用二维数组储存结果需要补充上边和左边的
 * 数组 2d dp，也可以用一位数组储存结果，更节约空间 1d dp。
 */
public class Solution221 {

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix.length==0? 0:matrix[0].length;
        int[] dp = new int[col+1];
        int ans = 0;
        for (int i = 1; i < row+1; i++){
            int prev = 0;
            for (int j = 1; j < col+1; j++){
                int temp = dp[j];
                if (matrix[i-1][j-1]=='1'){
                    dp[j] = Math.min(Math.min(dp[j-1],dp[j]),prev)+1;
                    ans = Math.max(ans,dp[j]);
                } else {dp[j] = 0;}
                prev = temp;
            }
        }
        return ans*ans;
    }
}
