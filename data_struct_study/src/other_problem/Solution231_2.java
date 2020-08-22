package other_problem;

/**
 * 利用 1000 & 0111 == 0
 */
public class Solution231_2 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

}
