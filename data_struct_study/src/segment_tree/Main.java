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
public class Main {

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> tree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(tree);

        System.out.println(tree.query(0, 2));
        System.out.println(tree.query(2, 5));
        System.out.println(tree.query(0, 5));
    }
}
