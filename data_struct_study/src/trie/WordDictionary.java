package trie;

import java.util.TreeMap;

class WordDictionary {

    public class Node {
        boolean isWord;
        TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node cur, String word, int index) {

        if (index == word.length()) {
            return cur.isWord;
        }

        char c = word.charAt(index);

        if ('.' != c) {
            if (cur.next.get(c) == null) {
                return false;
            }
            return match(cur.next.get(c), word, index + 1);
        } else {
            for (Character character:cur.next.keySet()) {
                if (match(cur.next.get(character), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }

    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */