package array_problem;
//https://leetcode-cn.com/problems/reverse-string/:反转字符串

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要创建另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 *
 */
public class Solution344 {

    // 1、递归解法会占用递归栈空间
    public String reverseString(String s) {
        // 1、初始化数组长度 & 当长度<=1说明不需要反转
        int len = s.length();
        if (len <=1 ) {
            return s;
        }

        // 2、在递归的过程中不断反转当前字符串的左子字符串与右子字符串，注意右在前，左在后，就是为了实现反转的效果
        String leftStr = s.substring(0, len / 2);
        String rightStr = s.substring(len / 2, len);
        return reverseString(rightStr) + reverseString(leftStr);
    }

    // 2、双指针解法，不断交换数组当前的首元素与尾元素，一个for循环搞定
    // 时间复杂度：O(n)，空间复杂度：O(1)
    public void reverseString(char[] nums) {
        // 1、初始化数组长度
        int len = nums.length;

        // 2、在for循环中不断交换当前的首元素与尾元素
        for (int l = 0, r = len - 1; l < r; ++l, --r) {
            char t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
        }
    }

    public static void main(String[] args) {
        // ["h","e","l","l","o"]
        String s = "hello";
        System.out.println(new Solution344().reverseString(s));
    }
}
