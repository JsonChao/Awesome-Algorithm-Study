package other_problem;

/**
 * 二进制表示只有一个 1 存在。
 */
public class Solution231 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }

}