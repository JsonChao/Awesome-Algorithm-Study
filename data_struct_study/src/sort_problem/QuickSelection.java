package sort_problem;

/**
 * 快速排序的 partition() 方法，会返回一个整数 j 使得 a[l..j-1]
 * 小于等于 a[j]，且 a[j+1..h] 大于等于 a[j]，此时 a[j]
 * 就是数组的第 j 大元素。
 * 可以利用这个特性找出数组的第 k 个元素。该算法是线性级别的，假设每次
 * 能将数组二分，那么比较的总次数为 (N+N/2+N/4+..)，直到找到第 k
 * 个元素，这个和显然小于 2N。
 */
public class QuickSelection<T extends Comparable<T>> extends Sort<T>{

    public T select(T[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (h > l) {
            int j = partition(nums, l, h);

            if (j == k) {
                return nums[k];

            } else if (j > k) {
                h = j - 1;

            } else {
                l = j + 1;
            }
        }
        return nums[k];
    }

    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v) && i != h) ;
            while (less(v, nums[--j]) && j != l) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    @Override
    public void sort(T[] nums) {

    }
}
