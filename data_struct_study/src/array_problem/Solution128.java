package array_problem;

import java.util.HashMap;

public class Solution128 {

    public int longestConsecutive(int[] nums) {
        // 1、创建 值:频率 哈希表
        HashMap<Integer, Integer> countForNum = new HashMap<>();
        for (int num:nums) {
            countForNum.put(num, 1);
        }

        // 2、求出哈希表中每一个值对应的最大序列长度
        for (int num:nums) {
            forward(countForNum, num);
        }

        // 3、统计哈希表中存储的最大序列长度
        return maxLen(countForNum);
    }

    private int forward(HashMap<Integer, Integer> countForNum, int num) {
        if (!countForNum.containsKey(num)) {
            return 0;
        }

        int cnt = countForNum.get(num);
        if (cnt > 1) {
            return cnt;
        }

        cnt = forward(countForNum, num + 1) + 1;
        countForNum.put(num, cnt);
        return cnt;
    }

    private int maxLen(HashMap<Integer, Integer> countForNum) {
        int max = 0;
        for (int num:countForNum.keySet()) {
            max = Math.max(max, countForNum.get(num));
        }

        return max;
    }

}