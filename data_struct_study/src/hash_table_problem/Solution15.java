package hash_table_problem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15:
 *      1、不同的三元组？
 *      2、如果有多个解，解的顺序？
 *      3、如果没有解。
 *
 * 三数之和：需要尤其注意去重，有些繁琐。
 */
public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            int l=i+1, r=nums.length-1;
            if(nums[i]>0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int sum=-1;
            while(l<r){
                sum=nums[i]+nums[l]+nums[r];
                if(sum>0) r--;
                else if (sum<0) l++;
                else{
                    list.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[l],nums[r])));
                    while(l<r&&nums[l]==nums[l+1]) l++;
                    while(l<r&&nums[r]==nums[r-1]) r--;
                    l++;r--;
                }
            }
        }
        return list;
    }
}
