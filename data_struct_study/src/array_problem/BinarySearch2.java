package array_problem;

/**
 * 对于有序数列，才能使用二分查找法（排序的作用）。
 * 100 万的数据量只需零点几秒。
 * 注意搜索开闭区间的设定，例如 [0, n - 1] 或 [0, n)。
 * 1962 才意识到的 bug：mid = (l + r) / 2 可能会产生整形溢出，推荐使用减法：l + (r-l)/2。
 * 如何写出正确的程序？
 *      1、明确变量的含义。
 *      2、循环不变量。
 *      3、小数据量调试。
 *      4、大数据量测试。
 */
public class BinarySearch2 {

    public static int binarySearch(Comparable[] arr, int n, Comparable target) {

        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return -1;
    }

}
