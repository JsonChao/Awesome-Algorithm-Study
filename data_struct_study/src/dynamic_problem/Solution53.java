package dynamic_problem;

/*
LeetCode Problem No.53:     https://leetcode.com/problems/maximum-subarray/
Idea:                       maxEndingHere, maxSoFar -> compare from the end of subarray instead of beginning
Time:                       1 ms, beat 99.96%
Space:                      39.5MB, beat 71.04%
*/

class Solution53 {

    // 一次循环，maxEndingHere储存以该点结尾的子序和，maxSoFar储存到该点的最大子序和。
    public int maxSubArray(int[] nums) {
        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];

        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];
        for (int i = 1; i<nums.length; i++){
            maxEndingHere = Math.max(nums[i],nums[i]+maxEndingHere);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}

