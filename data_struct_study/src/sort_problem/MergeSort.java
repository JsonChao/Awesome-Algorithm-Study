package sort_problem;

/**
 * 思路：归并排序的思想是不断地将数组分成两部分，分别进行排序，然后归并起来，
 * 归并就是将数组中两个已排序的部分归并成一个。归并排序是稳定性算法。
 * 时间复杂度：O(NlogN)
 * 空间复杂度：O(N)
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;

    // 归并方法：归并方法将数组中两个已经排序的部分归并成一个
    protected void merge(T[] nums, int l, int m, int h) {

        int i = l, j = m + 1;

        // 将数据复制到辅助数组
        for (int k = l; k <= h; k++) {
            aux[k] = nums[k];
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];
            } else if (j > h) {
                nums[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) <= 0) {
                nums[k] = aux[i++]; // 先进行这一步，保证稳定性
            } else {
                nums[k] = aux[j++];
            }
        }
    }

    @Override
    public void sort(T[] nums) {
        // 1、自顶向下归并排序
        // 不断地将一个大数组分成两个小数组去求解。
        // 因为每次都将问题对半分成两个子问题。
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);

        // 2、自底向上归并排序
        // 先归并那些微型数组，然后成对归并得到的微型数组。
//        int N = nums.length;
//        aux = (T[]) new Comparable[N];
//
//        for (int sz = 1; sz < N; sz += sz) {
//            for (int lo = 0; lo < N - sz; lo += sz + sz) {
//                merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
//            }
//        }
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int mid = l + (h - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{3, 5, 1, 2, 4};
        new MergeSort<Integer>().sort(nums);
    }

}
