package hash_table;


/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 */
class Solution1 {
    public int firstUniqChar(String s) {

        int[] hashTable = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hashTable[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (hashTable[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}