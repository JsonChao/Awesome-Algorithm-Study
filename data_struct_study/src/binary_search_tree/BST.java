package binary_search_tree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 树结构本身是一种天然的的组织结构，用树存储数据能更加高效地搜索。
 *
 * 二叉树：和链表一样，动态数据结构。
 * 1）、对于每一个节点，最多能分成2个节点，即左孩子和右孩子。
 * 2）、没有孩子的节点称为叶子节点。
 * 3）、每一个孩子节点最多只能有一个父亲节点。
 * 4）、二叉树具有天然的递归结构，即每个节点的左右子树都是二叉树。
 *
 * 注意：一个节点也是二叉树、空也是二叉树。
 *
 * 二叉树的分类：
 * 1）、满二叉树：除了叶子节点外，每个节点都有两个子节点。
 *
 *
 * 二分搜索树：
 * 1）、二分搜索树是一个二叉树，且其每一颗子树也是二分搜索树。
 * 2）、二分搜索树的每个节点的值大于其左子树的所有节点的值，小于其右子树的所有节点的值。
 * 3）、存储的元素必须有可比较性。
 * 4）、通常来说，二分搜索树不包含重复元素。如果想包含重复元素的话，只需定义：
 *     左子树小于等于节点；或者右子树大于等于节点。注意：数组和链表可以有重复元素。
 *
 * 什么是遍历操作？
 * 1）、遍历就是把所有的节点都访问一遍。
 * 2）、访问的原因和业务相关。
 * 3）、在线性结构下，遍历是极其容易的，但是在树结构中，遍历会稍微有点难度。
 *
 * 如何对二叉树进行遍历？
 *     对于遍历操作，两颗子树都要顾及。
 *
 * 前序遍历：最自然和常用的遍历方式。规律：根左右
 * 中序遍历：规律：左根右
 * 后序遍历：中序遍历的结果就是我们在二叉搜索树中所有数据排序后的结果。规律：左右根。应用：为二分搜索树释放内存。
 *
 * 心算出二叉搜索树的前中后序遍历：每一个二叉树都会被访问三次，从根节点出发，
 *      前序遍历：当一个节点被访问第一次就记录它。
 *      中序遍历：当一个节点被访问第二次的时候记录它。
 *      后序遍历：当一个节点被访问第三次的时候才记录它。
 *
 * 前序遍历的非递归实现（深度优先遍历）：需要使用栈记录下一步被访问的元素。
 * 对于二叉搜索树的非递归实现一般有两种写法：
 * 1）、经典教科书写法。
 * 2）、完全模拟系统调用栈的写法。
 *
 * 层序遍历（广度优先遍历）：需要使用队列记录当前出队元素的左右子节点。
 * 广度优先遍历的意义：
 * 1）、在于快速地查询要搜索的元素。
 * 2）、更快地找到问题的解。
 * 3）、常用于算法设计中——无权图最短路径。
 * 4）、联想对比图的深度优先遍历与广度优先遍历。
 *
 * 从二分搜索树中删除最小值与最大值：
 * 往左走的最后一个节点即是存有最小值的节点，往右走的最后一个节点即是存有最大值的节点。
 *
 * 删除二分搜索树种的任意元素：
 * 1）、删除只有左孩子的节点。
 * 2）、删除只有右孩子的节点。
 * 3）、删除具有左右孩子的节点：
 *      1、找到 s = min(d->right)
 *      s 是 d 的后继(successor)节点，也即 d 的右子树中的最小节点。
 *      s->right = delMin(d->right)
 *      s->left = d->left
 *      删除 d，s 是新的子树的根。
 *      2、找到 p = max(d->left)
 *      p 是 d 的前驱(predecessor)节点。
 *
 * 如何高效实现 rank（E 是排名第几的元素）？
 * 如何高效实现 select（查找排名第10的元素）？
 * 最好的方式是实现一个维护 size 的二分搜索树：
 *      给 Node 节点添加新的成员变量 size。
 * 维护 depth 的二分搜索树。
 * 维护 count 支持重复元素的二分搜索树。
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param e E
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 将元素 e 插入到以根节点 root 的二叉树中
     *
     * @param root Node
     * @param e E
     * @return 插入元素 e 后的二叉搜索树的根节点
     */
    private Node add(Node root, E e) {

        if (root == null) {
            size++;
            return new Node(e);
        }

        if (root.e.compareTo(e) < 0) {
            root.right = add(root.right, e);
        } else if (root.e.compareTo(e) > 0) {
            root.left = add(root.left, e);
        }

        return root;
    }

    /**
     * 判断是否包含元素 e
     *
     * @param e E
     * @return 是否包含元素 e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 判断以 root 为根节点的二叉搜索树种是否有元素 e
     *
     * @param root Node
     * @param e E
     * @return 以 root 为根节点的二叉搜索树种是否有元素 e
     */
    private boolean contains(Node root, E e) {

        if (root == null) {
            return false;
        }

        if (e.compareTo(root.e) < 0) {
            return contains(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            return contains(root.right, e);
        } else {
            return true;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 以根节点 root 的前序遍历
     *
     * @param root Node
     */
    private void preOrder(Node root) {

        if (root == null) {
            return;
        }

        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 前序遍历非递归实现，需要借助栈存储下一步要访问的元素
     */
    public void preOrderNR() {

        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 以根节点 root 的中序遍历
     *
     * @param root Node
     */
    private void inOrder(Node root) {

        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历
     *
     * @param root Node
     */
    private void postOrder(Node root) {

        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.e);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {

        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                q.add(cur.left);
            }

            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    /**
     * 获取最小值
     *
     * @return 最小值
     */
    public E minValue() {

        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }

        Node ret = minValue(root);
        return ret.e;
    }

    /**
     * 递归获取最小值
     *
     * @param cur 当前的根节点
     * @return Node
     */
    private Node minValue(Node cur) {
        if (cur.left == null) {
            return cur;
        }

        return minValue(cur.left);
    }

    /**
     * 获取最大值
     *
     * @return 最大值
     */
    public E maxValue() {

        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }

        Node ret = maxValue(root);
        return ret.e;
    }

    /**
     * 递归获取最大值
     *
     * @param cur 当前的根节点
     * @return Node
     */
    private Node maxValue(Node cur) {
        if (cur.right == null) {
            return cur;
        }

        return maxValue(cur.right);
    }

    /**
     * 删除最小节点
     *
     * @return 被删除的最小值
     */
    public E removeMinValue() {
        E minNode = minValue();
        root = removeMinValue(root);
        return minNode;
    }

    /**
     * 删除以 cur 为根节点的二叉搜索树中的最小节点
     *
     * @param cur 当前的根节点
     * @return 被删除最小节点后的新的根节点
     */
    private Node removeMinValue(Node cur) {
        if (cur.left == null) {
            Node rightNode = cur.right;
            cur.right = null;
            size--;
            return rightNode;
        }

        cur.left = removeMinValue(cur.left);
        return cur;
    }

    /**
     * 删除最大节点
     *
     * @return 最大节点
     */
    public E removeMaxValue() {
        E maxValue = maxValue();
        root = removeMaxValue(root);
        return maxValue;
    }

    /**
     * 删除以 cur 为根节点的二叉搜索树的最大节点
     *
     * @param cur 当前的根节点
     * @return 删除最大节点后的新的根节点
     */
    private Node removeMaxValue(Node cur) {
        if (cur.right == null) {
            Node leftNode = cur.left;
            cur.left = null;
            size--;
            return leftNode;
        }

        cur.right = removeMaxValue(cur.right);
        return cur;
    }

    /**
     * 删除指定的节点
     *
     * @param e E
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以 cur 为根节点中的指定节点
     *
     * @param cur Node
     * @param e E
     * @return 删除指定节点后的新的节点
     */
    private Node remove(Node cur, E e) {

        // 1、处理没有找到要删除的节点时的情况
        if (cur == null) {
            return null;
        }

        // 2、处理待删除的节点比当前的根节点 cur 小的情况
        if (e.compareTo(cur.e) < 0) {
            cur.left = remove(cur.left, e);
            return cur;
        } else if (e.compareTo(cur.e) > 0) {
            // 3、处理待删除的节点比当前的根节点 cur 大的情况
            cur.right = remove(cur.right, e);
            return cur;
        } else { // 4、处理待删除节点为根节点 cur 的情况

            // 1)、处理待删除节点左子树为空的情况
            if (cur.left == null) {
                Node rightNode = cur.right;
                cur.right = null;
                size--;
                return rightNode;
            }

            // 2）、处理待删除节点右子树为空的情况
            if (cur.right == null) {
                Node leftNode = cur.left;
                cur.left = null;
                size--;
                return leftNode;
            }

            // 3）、处理待删除节点左右子树都为空的情况
            // 找出待删除节点右子树的最小值
            Node processor = minValue(cur.right);
            // 将删除了待删除节点右子树的最小值后的新的根节点挂接到 processor 的右子树上
            processor.right = removeMinValue(cur.right);
            // 将待删除节点的左子树挂接到 processor 的左子树上
            processor.left = cur.left;

            // 释放游离的对象
            cur.left = cur.right = null;

            return processor;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    /**
     * 生成以 root 为根节点的 BST 字符串
     *
     * @param root Node
     * @param deep Deep
     * @param sb StringBuilder
     */
    private void generateBSTString(Node root, int deep, StringBuilder sb) {

        if (root == null) {
            sb.append(generateString(deep)).append("null\n");
            return;
        }

        sb.append(generateString(deep)).append(root.e).append("\n");
        generateBSTString(root.left, deep + 1, sb);
        generateBSTString(root.right, deep + 1, sb);
    }

    /**
     * 生成二叉树深度描述字符串
     *      -- 表示深度为1
     *      ---- 表示深度为 2
     *      依次类推
     *
     * @param deep Deep
     * @return 二叉树深度描述字符串
     */
    private String generateString(int deep) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deep; i++) {
            sb.append("--");
        }
        return sb.toString();
    }
}
