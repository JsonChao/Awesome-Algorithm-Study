package backstracking_problem;

import java.util.ArrayList;
import java.util.List;


/**
 * 排列问题
 * 时间复杂度：O(n*n!)
 * 空间复杂度：O(n)
 */
public class Solution46 {

    public List<List<Integer>> permute(int[] nums) {

        // 1、创建一个组合嵌套列表 & 组合列表 & 记录已访问元素的数组
        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        // 2、回溯
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    // 1,2,3 => 移除3,2 => 1,3,2 => 移除 2,3,1 => 2,1,3 => ...
    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {
        // 1、如果当前组合等于目标数组长度，则添加到嵌套列表中并返回到上一层
        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        // 2、遍历已访问数组，如果当前元素没有被访问过，则添加到记录组合列表中，并继续下一层回溯，
        // 回溯到底添加完组合列表后，则删除 当前 组合列表中最后一个元素
        for (int i = 0; i < visited.length; i++) {
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
        int[] nums = new int[]{1, 2, 3};
        new Solution46().permute(nums);
    }
}
