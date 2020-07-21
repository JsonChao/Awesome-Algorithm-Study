package heap_and_priority_queue;

import array.Array;


/**
 * 向堆中添加元素（上浮，Sift Up） O（logn）
 * 从堆中取出最大元素（下沉，Sift Down） O（logn）
 *
 * replace：取出最大元素后，放入一个新元素。
 * 实现1：可以先 extractMax，再 add，两次 O（logn）操作。
 * 实现2：可以直接将堆顶元素替换以后 Sift Down，一次 O（logn）操作。
 *
 * 将 n 个元素插入一个空堆中，算法复杂度为 O（nlogn）。
 * heapify（堆化）：算法复杂度为 O（n），只需要对所有非叶子节点进行 Sift Down 的操作。
 *
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private final Array<E> array;

    public MaxHeap() {
        array = new Array<>();
    }

    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * heapify（堆化）
     *
     * @param arr 静态数组
     */
    public MaxHeap(E[] arr) {
        array = new Array<>(arr);
        if (arr.length != 1) {
            for (int i = parent(arr.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    /**
     * 获取堆的大小
     *
     * @return 堆的大小
     */
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断堆是否为空
     *
     * @return 堆是否为空
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个节点的索引它所对应的父亲节点的索引
     *
     * @param index 某一个节点的索引
     * @return 一个节点的索引它所对应的父亲节点的索引
     */
    public int parent(int index) {
         return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个节点的索引它所对应的左孩子节点的索引
     *
     * @param index 某一个节点的索引
     * @return 一个节点的索引它所对应的左孩子节点的索引
     */
    public int leftNode(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个节点的索引它所对应的右孩子节点的索引
     *
     * @param index 某一个节点的索引
     * @return 一个节点的索引它所对应的右孩子节点的索引
     */
    public int rightNode(int index) {
        return 2 * index + 2;
    }

    /**
     * 向堆中添加元素
     *
     * @param e E
     */
    public void add(E e) {
        array.addLast(e);

        siftUp(array.getSize() - 1);
    }

    /**
     * 堆中元素的上浮
     *
     * @param k 要上浮元素的 index 下标
     */
    private void siftUp(int k) {

        while (k > 0 && array.query(parent(k)).compareTo(array.query(k)) < 0) {
            array.swap(k, parent(k));
            // 重置交换元素后 k 的新 index
            k = parent(k);
        }
    }

    /**
     * 看堆中的最大元素
     *
     * @return 堆中的最大元素
     */
    public E findMax() {

        if (array.getSize() == 0) {
            throw new IllegalArgumentException("heap size is 0~");
        }

        return array.query(0);
    }

    /**
     * 获取堆中最大元素
     *
     * @return 堆中最大元素
     */
    public E extractMax() {

        E ret = findMax();

        // 1、交换堆顶最大元素与最后一个元素的位置
        array.swap(0, array.getSize() - 1);
        // 2、删除最后一个元素：堆中最大元素
        array.deleteLast();

        // 3、下沉
        siftDown(0);

        return ret;
    }

    /**
     * 堆中元素的下沉
     *
     * @param k 要下沉的元素 index 下标
     */
    private void siftDown(int k) {

        // 1）、循环终止条件：当前下沉元素左节点的下标 < 数组的大小
        while (leftNode(k) < array.getSize()) {
            int j = leftNode(k);
            // 2）、如果有右节点，且右节点比左节点大，则 j 更新为右节点的下标
            if (j + 1 < array.getSize()
                    && array.query(j).compareTo(array.query(j + 1)) < 0) {
                j = rightNode(k);
            }

            // 3）、如果最大孩子节点的值 > 当前节点的值，
            // 则交换两者的值并更新下一轮要下沉的元素下标 k
            if (array.query(j).compareTo(array.query(k)) <= 0) {
                break;
            }

            array.swap(j, k);
            k = j;
        }
    }

    /**
     * 替换堆中最大的元素
     *
     * @param e e
     * @return 堆中最大的元素
     */
    public E replace(E e) {

        E ret = findMax();
        array.set(0, e);
        siftDown(0);
        return ret;
    }

}
