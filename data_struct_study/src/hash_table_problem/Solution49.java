package hash_table_problem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 49：
 *      1、字符集
 *      2、大小写敏感
 */
public class Solution49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map=new HashMap<>();
        for (String str:strs){
            char[] c=str.toCharArray();
            Arrays.sort(c);
            String s=String.valueOf(c);
            if(map.containsKey(s))
                map.get(s).add(str);
            else
                map.put(s,new ArrayList(Arrays.asList(str)));
        }
        return new ArrayList(map.values());
    }
}
