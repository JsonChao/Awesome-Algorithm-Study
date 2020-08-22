package backstracking_problem;


/**
 * 回溯法是经典人工智能的基础
 *      1、剪枝
 *      2、快速判断不合法的情况：
 *          竖向：col[i] 表示第i列被占用
 *          对角线1：dia1[i] 表示第i对角线1被占用—— 2*n-1个 i+j。对角线相加
 *          对角线2：dia2[i] 表示第i对角线2被占用—— 2*n-1个 i+j+n-1。对角线相减
 */
public class Solution37 {
}
