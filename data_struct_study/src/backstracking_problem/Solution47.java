package backstracking_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 排列问题
 * 时间复杂度：O(n*n!)
 * 空间复杂度：O(n)
 */
public class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 1、创建一个嵌套排列列表 & 排列列表 & 记录已访问元素的数组
        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        // 2、排序数组：保证相同的数字都相邻，然后每次填入的数一定是这个数
        // 所在重复数集合中「从左往右第一个未被填过的数字」，
        // 为了在后面回溯的时候便于排除重复元素
        Arrays.sort(nums);

        // 3、回溯
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {
        // 1、如果当前的排列列表达到nums的长度，则添加并返回至上一层
        if (permuteList.size() == nums.length) {
            permutes.add(permuteList);
            return;
        }

        // 2、遍历记录已访问的数组：不同于组合问题，排列问题首先需要去重，
        // 如果当前元素没被访问过，则将其添加到列表中，并继续访问下一层，
        // 访问到达添加后排列列表后，则删除当前排列列表中的最后一个元素
        for (int i = 0; i < visited.length; i++) {
            // 有序数组的去重处理：如果当前元素和前一个元素相等 & 前一个元素没被访问过
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                permuteList.add(nums[i]);
                backtracking(permuteList, permutes, visited, nums);
                permuteList.remove(permuteList.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2};
        new Solution47().permuteUnique(nums);
    }

}
