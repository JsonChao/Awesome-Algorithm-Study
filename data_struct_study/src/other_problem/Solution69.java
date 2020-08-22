package other_problem;

public class Solution69 {

    public int mySqrt(int x) {

        if (x <= 1) {
            return x;
        }

        int l = 0, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int sqrt = x / mid;
            if (mid == sqrt) {
                return mid;
            } else if (mid > sqrt) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return h;
    }

}
