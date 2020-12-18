package sort_problem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 归并排序将数组分为两个子数组分别排序，并将有序的子数组归并使得整个数组排序；
 * 快速排序通过一个切分元素将数组分为两个子数组，左子数组小于等于切分元素，
 * 右子数组大于等于切分元素，将这两个子数组排序也就将整个数组排序了。
 *
 * 快速排序是原地排序，不需要辅助数组，但是递归调用需要辅助栈。
 * 快速排序最好的情况下是每次都正好将数组对半分，这样递归调用次数才是最少的。
 * 这种情况下比较次数为 CN=2CN/2+N，复杂度为 O(NlogN)。
 *
 * 最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，如此这般。
 * 因此最坏的情况下需要比较 N2/2。为了防止数组最开始就是有序的，
 * 在进行快速排序时需要随机打乱数组。
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l)
            return;
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    // 取 nums[l] 作为切分元素，然后从数组的左端向右扫描直到找到第一个
    // 大于等于它的元素，再从数组的右端向左扫描找到第一个小于它的元素，
    // 交换这两个元素。不断进行这个过程，就可以保证左指针 i 的左侧元素
    // 都不大于切分元素，右指针 j 的右侧元素都不小于切分元素。当两个
    // 指针相遇时，将切分元素 nums[l] 和 nums[j] 交换位置。此时的j就是切分元素
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

}
