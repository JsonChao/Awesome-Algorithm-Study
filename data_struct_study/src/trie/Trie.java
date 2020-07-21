package trie;

import java.util.TreeMap;

/**
 * Trie 的删除操作
 *
 * Trie 的局限性：next 指针是 TreeMap 数据类型，key 值总数最多可以达到26种。
 * 改进方式：使用压缩字典树 Compressed Trie
 *
 * 三分搜索树（Ternary Search Tree）：时间换空间。
 *
 * 后缀树
 *
 * 子串查询算法：KMP、Boyer-Moore、Rabin-Karp。
 *
 * 文件压缩实际上也算是一种字符串压缩。
 *
 * 模式匹配：实现一个正则表达式引擎。
 *
 * 编译原理：字符串应用很多。
 *
 * DNA：超长字符串。
 */
public class Trie {

    public class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 向 trie 中添加一个新的单词
     *
     * @param word 单词
     */
    public void add(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = cur.next.get(c);
            if (node == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }


        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 判断单词 word 是否包含在 Trie 中
     *
     * @param word 单词
     * @return 单词 word 是否包含在 Trie 中
     */
    public boolean contains(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = cur.next.get(c);
            if (node == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    /**
     * 字符集合中是否有 prefix 前缀
     *
     * @param prefix 字符前缀
     * @return 是否有 prefix 前缀
     */
    public boolean hasPrefix(String prefix) {

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }

}
