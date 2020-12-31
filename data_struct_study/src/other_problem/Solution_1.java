package other_problem;


/**
 * 题目描述：将给出的32位整数x翻转。
 * 例1:x=123，返回321
 * 例2:x=-123，返回-321
 * 你有注意到翻转后的整数可能溢出吗？因为给出的是32位整数，则其数值范围为[−2^{31}, 2^{31} − 1]。
 * 翻转可能会导致溢出，如果反转后的结果会溢出就返回0。
 *
 * 关键点是如何判断溢出。
 * 推荐解答用的是用long类型存储结果，如果结果大于0x7fffffff或者小于0x80000000就溢出
 * 我的解法是每次计算新的结果时，再用逆运算判断与上一次循环的结果是否相同，不同就溢出
 */
public class Solution_1 {

    public int reverse(int x) {
        // 1、创建一个返回变量res
        int res = 0;

        // 2、当x不等于0时，进行反转操作
        while (x != 0){
            // 1）、初始化tail记录当前最后一位（-123%10==-3），newRes记录中间反转值
            int tail = x % 10;
            int newRes = res * 10 + tail;

            // 2）、异常处理：如果 (newRes-tail)/10!=res 说明产生了溢出，
            // 为什么？因为当newRes的值超过最大位数时，系统会将超过
            // 最大位数的高位二进制数扔掉，从而导致了newRes!=res*10+tail。
            // 此时计算的(newRes-tail)/10自然也就不等于res了。
            if ((newRes - tail) / 10 != res) {
                return 0;
            }

            // 3）、更新res与x
            res = newRes;
            x = x / 10;
        }

        // 3、直到x等于0才返回res
        return res;
    }

}
