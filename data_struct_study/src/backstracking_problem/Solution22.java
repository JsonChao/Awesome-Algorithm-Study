package backstracking_problem;

import java.util.ArrayList;
import java.util.List;


/**
 * 括号生成：【递归】，分只能添加“（”，只能添加“）”，和有可以添加
 * “（”又能添加“）”三种情况。注意“）”的个数时时刻刻不能超过“（”。
 */
public class Solution22 {

    public List<String> generateParenthesis(int n) {
        add("", 0, 0, n);
        return list;
    }
    List<String> list=new ArrayList<>();
    void add(String s, int count1, int count2, int n){
        if(count1==count2&&count1<n){
            s+="(";
            add(new String(s), count1+1, count2, n);
        }else if (count1==n){
            while (count2<n){
                s+=")"; count2++;
            }
            list.add(new String(s));
        }else{
            add(new String(s+"("), count1+1, count2, n);
            add(new String(s+")"), count1, count2+1, n);
        }
    }
}
