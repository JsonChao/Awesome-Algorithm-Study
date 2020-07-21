package set;


import java.util.TreeSet;

/**
 * Leetcode 804. Unique Morse Code Words
 * https://leetcode.com/problems/unique-morse-code-words/description/
 *
 * 有序集合中的元素具有顺序性：基于搜索树的实现。
 * 无序集合中的元素没有顺序性：基于哈希表的实现。
 * 多重集合：集合中的元素可以重复，可以在允许重复的二叉搜索树上包装一层即可实现。
 *
 */
public class Solution1 {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<Object> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                sb.append(codes[word.charAt(j) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}