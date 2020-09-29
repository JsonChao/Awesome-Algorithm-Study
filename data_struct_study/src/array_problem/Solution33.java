package array_problem;


/**
 * 搜索旋转排序数组：先二分查找旋转点（迭代或者递推），
 * 再二分查找目标（递推）。二分查找可以用递归/循环，
 * 是很常见的写法。需注意分段和索引范围问题
 * （没有必要为了排除某个别索引把分段弄得特别严谨）。
 */
public class Solution33 {

    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len==0) return -1;
        if (len==1) return nums[0]==target? 0:-1;

        int r = findRotation(nums,0,len-1);
        if (r==-1) return findTarget(nums,0,len-1, target);
        if (nums[0]>target) return findTarget(nums,r,len-1,target);
        else if (nums[0]<target) return findTarget(nums,0,r-1,target);
        else return 0;
    }

    private int findRotation(int[] nums, int a, int b)
    {
        if (b == a) return -1;

        // iteration is also okay here
        int m = (a+b)/2;
        if (nums[m]>nums[m+1]) return m+1;
        else return (Math.max(findRotation(nums,a,m),findRotation(nums,m+1,b)));
    }

    private int findTarget(int[] nums, int a, int b, int target)
    {
        if (a==b) return nums[a]==target? a:-1;
        if (target>nums[b]||target<nums[a]) return -1;

        int m = (a+b)/2;
        if (nums[m]>target) return findTarget(nums,a,m,target);
        else if (nums[m]<target) return findTarget(nums,m+1,b,target);
        else return m;
    }
}
