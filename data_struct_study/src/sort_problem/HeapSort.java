package sort_problem;

/**
 * 把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，
 * 那么就可以得到一个从尾到头的递减序列，从正向来看就是一个
 * 递增序列，这就是堆排序。
 *
 * 一个堆的高度为 logN，因此在堆中插入元素和删除最大元素的复杂度都为 logN。
 * 对于堆排序，由于要对 N 个节点进行下沉操作，因此复杂度为 NlogN。
 * 堆排序是一种原地排序，没有利用额外的空间。
 * 现代操作系统很少使用堆排序，因为它无法利用局部性原理进行缓存，
 * 也就是数组元素很少和相邻的元素进行比较和交换。
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 数组第 0 个位置不能有元素
     */
    @Override
    public void sort(T[] nums) {
        int N = nums.length - 1;
        for (int k = N / 2; k >= 1; k--)
            sink(nums, k, N);

        while (N > 1) {
            swap(nums, 1, N--);
            sink(nums, 1, N);
        }
    }

    private void sink(T[] nums, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(nums, j, j + 1))
                j++;
            if (!less(nums, k, j))
                break;
            swap(nums, k, j);
            k = j;
        }
    }

    private boolean less(T[] nums, int i, int j) {
        return nums[i].compareTo(nums[j]) < 0;
    }
}
