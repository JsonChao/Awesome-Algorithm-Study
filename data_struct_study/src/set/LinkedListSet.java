package set;

import LinkedList.LinkedList;

import java.util.ArrayList;

/**
 * BST 和 LinkedList 都属于动态数据结构
 *
 * BSTSet 和 LinkedListSet 时间复杂度对比（h 为二分搜索树的高度）
 *
 *              LinkedListSet   BSTSet      最优        平均       最差（二分搜索树退化为线性链表时）
 * 增 add            O(n)        O(h)      O(logn)    O(logn)     O(n)
 * 查 isContains     O(n)        O(h)      O(logn)    O(logn)     O(n)
 * 删 remove         O(n)        O(h)      O(logn)    O(logn)     O(n)
 *
 * O(h) => O(logn) 过程？
 *
 * h = log2(n+1) => O(log2n) => O(logn)
 *
 * O(n) 与 O(logn) 的差距？
 *
 *              log(n)        n        对比结果
 * n = 16        4           16        相差4倍
 * n = 1024      10          1024      相差100倍
 * n = 100万     20          100万      相差5万倍
 *
 * 应用场景：
 *      1）、客户统计
 *      2）、词汇量统计
 *
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        this.list = new LinkedList<E>();
    }

    @Override
    public void add(E e) {
        if (!list.isContains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean isContains(E e) {
        return list.isContains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {

        ArrayList<String> words1 = new ArrayList<>();

        System.out.println("pride-and-prejudice");

        if (FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set = new LinkedListSet<>();
            for (String item:words1) {
                set.add(item);
            }

            System.out.println("Total different words: " + set.getSize());
        }


        ArrayList<String> words2 = new ArrayList<>();

        System.out.println("a-tale-of-two-cities");

        if (FileOperation.readFile("a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set = new LinkedListSet<>();
            for (String item:words2) {
                set.add(item);
            }

            System.out.println("Total different words: " + set.getSize());
        }
    }
}
