package array_problem;


/**
 * 最长回文子串：注意要分奇偶情况分别讨论。
 */
public class Solution5 {

    public String longestPalindrome(String s) {
        if (s==null||s.length()<1) return "";
        int maxLen=0, id=-1;
        for(int i=0; i<s.length(); i++){
            int len1=expand(s,i,i);
            int len2=expand(s,i,i+1);
            int len=Math.max(len1,len2);
            if (len>=maxLen){
                maxLen=len;id=i;
            }
        }
        return s.substring(id-(maxLen-1)/2+1, id+maxLen/2);
    }

    int expand(String s, int l, int r){
        while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;r++;
        }
        return r-l+1;
    }
}
