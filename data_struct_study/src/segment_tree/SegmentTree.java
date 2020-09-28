package segment_tree;

/**
 * 为什么要使用 线段树？
 *  1、区间染色：m 次操作后，我们可以在区间 [i,j] 内看见多少种颜色？
 *                      使用数组实现      线段树
 *  染色操作（更新操作）      O（n)        O（logn）
 *  查询操作（查询区间）      O（n)        O（logn）
 *
 *  2、区间查询：查询一个区间的 最大值、最小值、后者区间数字和。
 *  更新：更新区间中一个元素或者一个区间的值。
 *     实质：基于区间的统计查询。
 *
 *     segment_tree.png
 *
 * 线段树不是完全二叉树，但是线段树和堆都是平衡二叉树。
 * 我们可以把线段树看作是一个满二叉树，对于最后一层不存在的节点，
 * 看作是 null 即可，所以我们可以使用数组来表示满二叉树。
 *
 * 对于满二叉树：
 * 1）、h 层，一共有 2^h - 1 个节点（大约是 2^h）
 * 2）、最后一层（h - 1层），有 2^(h - 1) 个节点。
 * 3）、最后一层的节点数大致等于前面所有层节点之和。
 *
 * 如果区间有 n 个元素，数组表示需要有多少节点？
 *  区间固定时，需要 4n 的空间。
 *
 * 线段树的区间查询、指定 index 位置的值更新
 *
 * 线段树的区间更新：使用指定 index 位置的值更新套路需要更新叶子节点的值，时间复杂度从 O（logn）提升到 O（n），
 * 可以使用 懒惰更新：使用 Lazy 数组记录未更新的内容。
 *
 * 上述都是一维线段树，二维线段树中每一个节点都有4个孩子节点，三维线段树中每一个节点都有8个孩子节点。
 *
 * 使用链表实现的动态线段树，可以避免数组实现的线段树中要多存储 null 节点的问题。
 *
 * 区间操作相关的另一个重要的数据结构：树状数组（Binary Index Tree）。
 *
 * 区间相关的问题被归类为 RMQ（Range Minimum Query）问题。
 *
 */
public class SegmentTree<E> {

    private Merger<E> merger;
    private E[] data;
    private E[] tree;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;

        data = (E[])new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 在 treeIndex 的位置创建表示区间 [l...r] 的线段树
     *
     * @param treeIndex treeIndex 位置
     * @param l left index
     * @param r right index
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {

        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回搜索区间 [queryL...queryR] 的值
     *
     * @param queryL queryLeft
     * @param queryR queryRight
     * @return 搜索区间 [queryL...queryR] 的值
     */
    public E query(int queryL, int queryR) {

        if ((queryL < 0 || queryL >= data.length) ||
                (queryR < 0 || queryR >= data.length) ||
                queryL > queryR) {
            throw new IllegalArgumentException("index is illegal~");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以 treeIndex 为根节点、区间为 [l...r] 的线段树中，返回搜索区间 [queryL...queryR] 的值
     *
     * @param treeIndex 当前线段树的根节点
     * @param l left
     * @param r right
     * @param queryL queryLeft
     * @param queryR queryRight
     * @return 搜索区间 [queryL...queryR] 的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        // 1、搜索区间完全等同于当前线段树的区间
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        // 2、搜索区间仅存在当前线段树区间的左区间或右区间
        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        // 3、搜索区间在当前线段树的区间的左右区间均存在
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 将 index 的值更新为 e
     *
     * @param index index
     * @param e E
     */
    public void set(int index, E e) {

        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal~");
        }

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在以 treeIndex 为根的分段树中，更新 index 位置的值为 e
     *
     * @param treeIndex treeIndex
     * @param l left
     * @param r right
     * @param index index
     * @param e E
     */
    private void set(int treeIndex, int l, int r, int index, E e) {

        // 1、区间范围缩小为1时，表示找到了要更新的位置
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        // 2、通过分治思想寻找要更新的位置
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[index] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    /**
     * 获取指定下标的元素
     *
     * @param index 下标
     * @return 指定下标的元素
     */
    public E get(int index) {

        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal!");
        }

        return data[index];
    }

    /**
     * 在一个由完全二叉树表示的数组中，获取当前节点的左孩子的下标
     *
     * @param index 当前节点的下标
     * @return 当前节点的左孩子的下标
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 在一个由完全二叉树表示的数组中，获取当前节点的右孩子的下标
     *
     * @param index 当前节点的下标
     * @return 当前节点的右孩子的下标
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }


}
