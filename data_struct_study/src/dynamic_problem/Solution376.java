package dynamic_problem;


/**
 * 最长公共子序列 LCS
 *      1、LCS(m, n) S1[0...m] 和 S2[0...n] 的最长公共子序列的长度。
 *      S1[m] == S2[n]: LCS(m, n) = 1 + LCS(m-1, n-1)
 *      S1[m] != S2[n]: LCS(m, n) = max( LCS(m-1, n), LCS(m, n-1))
 *
 * dijkstra 单源最短路径算法也是动态规划
 *      1、shortestPath(i) 为从start到i的最短路径长度。
 *      shortestPath(x) = min(shortestPath(a) + w(a->x))
 */
public class Solution376 {
}
