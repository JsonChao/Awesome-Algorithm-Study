package hash_table;


import avl.AVLTree;
import avl.BST;
import avl.FileOperation;
import red_black_tree.RBTree;

import java.util.ArrayList;

/**
 * 什么是哈希函数？
 *
 * 哈希函数：将 "键" 转换为 "索引"，每一个 "键" 对应唯一的一个索引。
 *
 * 很难保证每一个 "键" 通过哈希函数的转换对应不同的 "索引"，因此会产生哈希冲突。
 * "键" 通过哈希函数得到的 "索引" 分布越均匀越好。
 *
 * 在哈希表（空间换时间）上操作，主要要考虑如何解决哈希冲突。
 *
 * 哈希函数的设计
 *
 * 通用的方法——转换为整形处理：
 *      1、小范围正整数直接使用。
 *      2、小范围负整数进行偏移。（-100~100 => 0~200）
 *      3、大整数：通常做法——取模，例如对身份证号取模后6位，即取最后6位，
 *      因为倒数第5、6位是表示1~31，所以会导致分布不均匀的情况。
 *      一个简单的解决办法就是模一个素数，例如7.
 *      素数取值表：http://planetmath.org/goodhashtableprimes
 *      4、浮点型：在计算机中都是32位或64位的二进制表示，只不过计算机解析成了浮点数。
 *      我们可以直接将二进制转换为整形来处理。
 *      5、字符串：
 *          166 = 1 * 10^2 + 6 * 10^1 + 6 * 10^0
 *          code = c * 26^3 + o * 26^2 + d * 26^1 + e * 26^0
 *          code = c * B^3 + o * B^2 + d * B^1 + e * B^0
 *          hash(code) = (c * B^3 + o * B^2 + d * B^1 + e * B^0) % M(素数）
 *          降低计算量：
 *          hash(code) = ((((c * B) + o) * B + d) * B) + e) % M
 *          避免整形溢出：
 *          hash(code) = ((((c % M） * B + o) % M * B + d) % M * B + e) % M
 *          代码形式：
 *          int hash = 0;
 *          for(int i = 0;i < s.length();i++) {
 *              hash = (hash * B + s.charAt(i)) % M;
 *          }
 *      6、复合类型：也是利用字符串的上述公式，例如 Date：year、month、day。
 *      hash(code) = (((date.year % M) * B + date.month) % M * B + date.day) % M
 * 设计原则：
 *      1、一致性：如果 a == b，则 hash(a) == hash(b)
 *      2、高效性：计算高效简便
 *      3、均匀性：哈希值均匀分布
 *
 * Java 中 Object 的 hashCode 是根据每一个对象的地址映射成整形。
 *
 * 哈希冲突的处理
 *      1、链地址法（Separate Chaining）：
 *      去掉 k1 符号：（hashCode(k1) & 0x7fffffff) % M，& 0x7fffffff 表示将 k1 最高位变为0。
 *      HashMap 本质就是一个 TreeMap 数组。
 *      HashSet 本质就是一个 TreeSet 数组。
 *      Java8 之前，每一个位置对应一个链表，Java8 开始，当哈希冲突达到一定程度
 *      （平均来讲，每一个位置存储的元素树多于某一个程度了），每一个位置从链表转成红黑树。
 *      HashMap 容量的选取非常重要，恰当的容量能够避免数组扩容和尽量减少 hash 冲突。
 *      算法复杂度分析：
 *          总共有 M 个地址，如果放入哈希表的元素为 N。
 *          如果地址是链表：O(N/M)
 *          如果地址是平衡树：O(log(N/M))
 *          为了实现均摊复杂度 O(1)，需要对哈希表进行动态空间处理，即
 *          1、平均每个地址承载的元素多过一定程度，即扩容 N/M >= upperTol
 *          2、平均每个地址承载的元素少过一定程度，即缩容 N/M < lowerTol
 *          3、对于哈希表来说，元素树从 N 增加到 upperTol * N，地址空间翻倍
 *          4、每个操作在 O(LOWER_TOL)~O(UPPER_TOL) => O(1)
 *
 * 更复杂的动态空间处理方法
 *      扩容 M -> 2 * M => 不是素数
 *      解决方案：使用素数取值表中的值：http://planetmath.org/goodhashtableprimes
 *
 * 哈希表相比 AVL、红黑树 而言，牺牲了顺序性，换来了更好的性能。
 * 集合、映射：
 *      1、有序集合（TreeSet）、有序映射（TreeMap）：平衡树。
 *      2、无序集合（HashSet）、无序映射（HashMap）：哈希表。
 *
 * 更多的哈希冲突处理方法：
 *      1、开发地址法（线性探测法）：对于开放地址法来说，每一个地址就是直接存元素，
 *      即每一个地址都对所有的元素是开放的。如果有冲突，直接存到当前索引的下一个索引对应的位置，
 *      如果位置还被占用，继续往下寻找即可。
 *      (平方探测法)： +1、+4、+9、+16。
 *      (二次哈希法)：使用另外一个 hash 算法来计算出下一个位置要去哪儿，hash2(key)。
 *      负载率（LoaderFactory）：元素占总容量比，超过它就需要进行扩容，
 *      一般选取为50%，选取合适时间复杂度可以到达 O(1)。
 *      2、再哈希法（Rehashing）：使用另外一个 hash 算法来计算出下一个位置要去哪儿。
 *      3、Coalesced Hashing：综合了 Separate Chaining 和 Open Addressing。
 *
 */
public class Main {

    public static void main(String[] args) {
//        int a = 42;
//        System.out.println(((Integer)a).hashCode());
//
//        int b = -42;
//        System.out.println(((Integer)b).hashCode());
//
//        double c = 3.1415926;
//        System.out.println(((Double)c).hashCode());
//
//        String d = "imooc";
//        System.out.println(d.hashCode());
//
//        System.out.println(Integer.MAX_VALUE + 1);
//        System.out.println();
//
//        Student student = new Student(3, 2, "Bobo", "Liu");
//        System.out.println(student.hashCode());
//
//        HashSet<Student> set = new HashSet<>();
//        set.add(student);
//
//        HashMap<Student, Integer> scores = new HashMap<>();
//        scores.put(student, 100);
//
//        Student student2 = new Student(3, 2, "Bobo", "Liu");
//        System.out.println(student2.hashCode());

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for(String word: words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for(String word: words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RBTree
            startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }

            for(String word: words)
                rbt.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");


            // Test HashTable
            startTime = System.nanoTime();

            // HashTable<String, Integer> ht = new HashTable<>();
            HashTable<String, Integer> ht = new HashTable<>();
            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }

            for(String word: words)
                ht.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }

        System.out.println();
    }
}
