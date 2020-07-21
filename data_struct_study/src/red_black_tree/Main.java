package red_black_tree;

import avl.AVLTree;
import avl.BST;
import avl.FileOperation;

import java.util.ArrayList;


/**
 * 算法导论中给出的5条红黑树的定义太生硬，并不能让人理解到底什么才是红黑树。
 * 而算法4的作者 Robert Sedgewick 是红黑树的发明人之一，而他正是现代计算机科学之父
 * Donald Knuth 的弟子，可以挑战下 Donald Knuth 的两大著作：《计算机编程的艺术》
 *
 * 红黑树与2-3树的等价性。理解了2-3树，对我们理解红黑树与B类树有巨大帮助。
 *
 * 2-3树：
 *      1、满足二分搜索树的基本性质。
 *      2、节点可以存放一个元素或两个元素。
 *      3、每个节点有2个或者3个孩子——这正是2-3树名称的由来。
 *      4、存放一个元素二个孩子的节点称为2节点，存放二个元素三个孩子的节点成为3节点。
 *      5、2-3树是一颗绝对平衡（对于任意一个节点来说，左右子树的高度一定是相同的）的数。
 *
 * 2-3树如何维持绝对的平衡？
 *      1、添加节点永远不会添加到一个空节点，而是跟最后的一个叶子节点做融合。
 *      2、暂时形成的一层4节点会分裂成两层2节点，暂时形成的两层4节点会分裂成三层2节点。
 *      3、生成的新的树中根节点会跟顶部根节点做融合（形成3节点或4节点），以保持绝对平衡。
 *
 * 红黑树与2-3树的等价性：
 *      1、红黑树就是只有一个元素节点的2-3树。
 *      2、红黑的连接线表示相连节点原先是同在一个3节点。
 *      此时红黑的节点与它相连的黑色父节点一起表示之前的一个3节点。
 *      所以所有红黑的节点一定都是向左倾斜的。
 *      即3节点的左侧的节点在红黑树中是一个红色的节点。
 *      3、2节点的根是黑色的，3节点的右侧节点是红色的，左侧是黑色的。
 *
 * 红黑树：
 *      1、初始化的根节点为红色。
 *      2、红黑树是保持"黑平衡"的二叉树，它的本质就是在于2-3树是一个保持绝对平衡的二叉树。
 *      由于是"黑平衡"，所以严格意义上，不是平衡二叉树。
 *      3、最大高度 2logn：即每一个节点都是一个3节点，3节点对应一个黑色与红色连接的树模板。
 *      但它的时间复杂度依然是 O(logn)的，虽然比 AVL 树（偏查询）总体性能要慢，但它的重要性在于其添加
 *      元素与删除元素相比于 AVL 树来说要快速一些。
 *
 * 理解了红黑树与2-3树的等价性，我们就可以很容易理解《算法导论》中对于红黑树的5点定义：
 *      1、每一个节点可能是红色或黑色。
 *      2、根节点是黑色的。
 *      3、每一个叶子节点（最后的空节点）一定是黑色的。
 *      4、如果一个节点是红色的，那么他的孩子节点一定都是黑色。
 *      5、从任意一个节点到叶子节点，经过的黑色节点一定是一样多的。
 *
 * 它们可以简化为：
 *     1、所有节点非红即黑。
 *     2、根节点为黑色。
 *     3、最后的 NULL 节点为黑。
 *     4、红节点的孩子一定为黑。（红色性质）
 *     5、黑平衡。（黑色性质）
 *
 * 对于红黑树，任何不平衡都会在三次旋转内解决。红黑树不仅能和2-3树等价，
 * 还和2-4树等价，但是4-node只能以RBR的形状表示。
 * 用2-4树去理解《算法导论》中红黑树的实现。
 *
 *
 */
public class Main {

    public static void main(String[] args) {

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
        }

        System.out.println();
    }
}
