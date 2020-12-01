package other_problem;

/**
 * 关键点是如何判断溢出。
 * 推荐解答用的是用long类型存储结果，如果结果大于0x7fffffff或者小于0x80000000就溢出
 * 我的解法是每次计算新的结果时，再用逆运算判断与上一次循环的结果是否相同，不同就溢出
 */
public class Solution_1 {

    public int reverse(int x) {
        int res=0;
        while(x!=0){
            //最后一位
            int tail=x%10;
            int newRes=res*10+tail;
            //如果newRes-tail)/10!=res说明产生了溢出
            if((newRes-tail)/10!=res)
                return 0;
            res=newRes;
            x=x/10;
        }
        return res;
    }
}
