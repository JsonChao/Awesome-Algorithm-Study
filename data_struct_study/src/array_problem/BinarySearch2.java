package array_problem;

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
