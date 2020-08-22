package hash_table_problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution451 {

    public String frequencySort(String s) {

        // 1、创建 HashMap 字符：频率
        HashMap<Character, Integer> freqCharMap = new HashMap<>();
        for (Character c:s.toCharArray()) {
            freqCharMap.put(c, freqCharMap.getOrDefault(c, 0) + 1);
        }

        // 2、创建桶 ArrayList[] 频率：字符列表
        List<Character>[] freqCharList = new ArrayList[s.length() + 1];
        for (char c:freqCharMap.keySet()) {
            int f = freqCharMap.get(c);
            if (freqCharList[f] == null) {
                freqCharList[f] = new ArrayList<>();
            }
            freqCharList[f].add(c);
        }

        // 3、拼装按频率高-低的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = freqCharList.length - 1; i >= 0; i--) {
            if (freqCharList[i] != null) {
                for (char c:freqCharList[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }

}
