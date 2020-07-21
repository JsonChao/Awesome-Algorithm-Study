package trie;

import set.BSTSet;
import set.FileOperation;

import java.util.ArrayList;

/**
 * Trie:字典树，前缀树，多叉树
 *
 * 作用：专门为处理字符串而设计的
 *
 * 字典与 Trie 的比较：
 *
 * 字典：如果有 n 个条目，使用平衡二叉树查询的复杂度为 O（logn），100万(2^20)的数据量其 logn 大概为 20。
 * Trie：查询的时间复杂度为 O（w），w 为查询单词的的长度，大多数单词的长度小于10。
 *
 * Trie 中每一个节点都有若干个指向下一个节点的指针，考虑不同的语言，不同的情境。
 *
 * 在我们编写的归并排序和快速排序算法中，对于n比较小的情况，我们会转而使用理论复杂度更高的插入排序去优化。
 * 虽然插入排序的理论复杂度比归并排序，快速排序都要高，但是只有在n很大的情况才能显现出来，在n很小的情况下，插入排序更快.
 *
 * 时间复杂度 BST > TreeMap Trie > HashMap Trie > 数组 Trie
 *
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words) &&
                FileOperation.readFile("a-tale-of-two-cities.txt", words)){

            // Test BST
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for(String word: words)
                set.add(word);

            for(String word: words)
                set.isContains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ---

            // Test TreeMap Trie
            startTime = System.nanoTime();

            Trie trie = new Trie();
            for(String word: words)
                trie.add(word);

            for(String word: words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("TreeMap Trie: " + time + " s");

            // ---

            // Test HashMap Trie
            startTime = System.nanoTime();

            Trie2 trie2 = new Trie2();
            for(String word: words)
                trie2.add(word);

            for(String word: words)
                trie2.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("HashMap Trie: " + time + " s");

            // ---

            // Test Array(Map) Trie
            startTime = System.nanoTime();

            Trie3 trie3 = new Trie3();
            for(String word: words)
                trie3.add(word);

            for(String word: words)
                trie3.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Array(Map) Trie: " + time + " s");
        }
    }
}
