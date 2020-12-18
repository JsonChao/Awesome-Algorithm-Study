package array_problem;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 1、排序
 * O(nlogn)
 * O(1)
 * 2、堆排序：空间换时间
 * O(nlogk)
 * O(k)
 * 3、利用快排 partition 中，将 pivot 放置在了正确的位置上的性质。
 * O(n)
 * O(1)
 */
public class Solution215 {


    public int findKthLargest(int[] nums, int k) {

        // 1、取k为第k个最小的元素 & 初始化窗口左右边界
        k = nums.length - k;
        int l = 0, r = nums.length - 1;

        // 2、当窗口的长度有效，即左边界小于右边界时
        while(l < r) {
            // 3、利用快排的partition函数找到切分元素对应的位置j，也就是第j个最小的元素
            int j = partition(nums, l, r);
            // 4、如果找到的第j个最小元素的下标就是目标元素的下标，则break返回元素即可
            if (j == k) {
                break;
            } else if (j < k) {
                // 如果小于，则更新窗口左边界为j+1,否则更新窗口右边界为j-1
                l = j + 1;
            } else {
                r = j - 1;
            }
        }

        return nums[k];
    }

    private int partition(int[] nums, int l, int r) {

        // 1）、初始化窗口的左右边界，注意右边界要+1，便于后面复用通用的代码逻辑
        int i = l, j = r + 1;

        // 2）、取第一个元素为切分元素
        int v = nums[l];

        // 3）、开启一个while无限循环来找切分元素
        while (true) {
            // 4）、从左到右找到第一个比切分元素大的元素 & 这个元素小于右边界
            while (nums[++i] < v && i < r) ;
            // 5）、从右到左找到第一个比切分元素小的元素 & 这个元素大于左边界
            while (nums[--j] > v && j > l) ;
            // 6）、如果比切分元素大的元素与比切分元素小的元素相遇了，则break
            if (i >= j) {
                break;
            }

            // 7）、否则，交互i和j的值
            swap(nums, i, j);
        }

        // 8）、最后，交换l和j的值
        swap(nums, l, j);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        // [3,2,1,5,6,4] 和 k = 2
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(new Solution215().findKthLargest(nums, 2));
    }

}
