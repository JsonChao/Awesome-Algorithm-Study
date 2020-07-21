package array_problem;

/**
 * 二分搜索：[0, n-1] 区间搜索版
 */
public class BinarySearch {

    public static int binarySearch(Comparable[] arr, int n, Comparable target){

        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }
            if (arr[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

}
