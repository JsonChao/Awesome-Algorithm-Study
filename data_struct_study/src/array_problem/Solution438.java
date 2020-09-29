package array_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 438：
 *      1、字符集范围？英文小写字母。
 *      2、返回的解的顺序？任意。
 *
 * 找到字符串中所有字母异位词：【滑动窗口】。
 */
public class Solution438 {

    public List<Integer> findAnagrams(String s, String p) {
        if(p==null||s==null||p.length()==0||s.length()==0
                ||p.length()>s.length())
            return new ArrayList<Integer>();
        int[] record=new int[26];
        List<Integer> list=new ArrayList<>();
        int sum=0;
        for(int i=0; i<p.length(); i++){
            char c=p.charAt(i);
            record[c-'a']++; sum++;
        }
        for(int i=0; i<p.length(); i++){
            char c=s.charAt(i);
            record[c-'a']--;
            if (record[c-'a']>=0) sum--;
        }
        if (sum==0) list.add(0);
        for (int i=p.length(); i<s.length(); i++){
            char c=s.charAt(i-p.length());
            record[c-'a']++;
            if (record[c-'a']>0) sum++;
            c=s.charAt(i);
            record[c-'a']--;
            if (record[c-'a']>=0) sum--;
            if (sum==0) list.add(i-p.length()+1);
        }
        return list;
    }
}
