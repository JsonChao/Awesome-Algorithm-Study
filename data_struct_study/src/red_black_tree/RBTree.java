package red_black_tree;

import avl.FileOperation;

import java.util.ArrayList;


/**
 * （特殊的红黑树：左倾红黑树）手写红黑树添加节点的逻辑，只要在二分搜索树实现的基础上对添加逻辑进行修改即可：
 *      1、永远添加红色节点：Node 构造器中初始化 color 为 RED。
 *      2、保持根节点为黑色节点。
 *      3、如果红色节点在右侧，需要进行左旋转到左侧。
 *      （注意：左旋转这个子过程产生的树结构并不一定满足红黑树的性质）
 *      4、在2-3树中的2节点添加一个新节点后会进行颜色翻转（flipColors），即根黑子红变为根红子黑。
 *      5、如果红色节点在左侧，需要进行右旋转到右侧。
 *      6、当前节点表示跟父节点融合在一起时需要设置为红色。
 *      7、添加时的最复杂的情况：在2-3树的3节点中添加一个中间值节点，此时需要
 *      维护红黑树的逻辑链条——左旋转、右旋转、颜色翻转。而且它的组成过程正符合其它的添加情况，
 *      在实现时可以直接复用。red_black_add.png
 *      8、红黑树性质的维护时机：和 AVL 树一样，添加节点后回溯向上维护。
 *
 * 红黑树的性能总结：
 *      1、对于完全随机的数据，普通的二分搜索树即可。
 *      但它的缺点在于极端情况下会退化成链表或者是高度不平衡。
 *      2、对于查询较多的情况，AVL 树更好。
 *      3、而红黑树综合统计性能（综合增删改查所有操作）更优，
 *      尤其是添加、删除操作，但是红黑树牺牲了平衡性（2logn 的高度）。
 *      4、手写红黑树删除节点的逻辑需要考虑的请求比添加逻辑更复杂，更琐碎，仅适合装逼。
 *
 * 右倾红黑树实现？
 *
 * 另一种统计性能优秀的树结构：Splay Tree（伸展树），
 * 利用了局部性原理——刚被访问的内容下次高概率被再次访问。
 *
 * 基于红黑树的 Map 和 Set：java.util 中的 TreeMap 和 TreeSet 就是基于红黑树实现的。
 *
 * 红黑树的其它实现：查阅算法导论中的红黑树的实现（添加与删除）。
 *
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    /**
     * 判断当前节点是否是红色
     *
     * @param node Node
     * @return 当前节点是否是红色
     */
    public boolean isRed(Node node) {

        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 颜色翻转：在2-3树中的2节点添加一个新节点后会进行颜色翻转（flipColors），即根黑子红变为根红子黑。
     *
     * @param node 当前子树的根节点
     */
    private void flipColors(Node node) {

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     *     node                   x
     *    /   \     右旋转       /  \
     *   x    T2   ------->   y   node
     *  / \                       /  \
     * y  T1                     T1  T2
     *
     * @param node 需要右旋转的节点
     * @return 右旋转后的根节点
     */
    private Node rightRotate(Node node) {

        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        // 表示需要融合的元素设置为红色
        node.color = RED;

        return x;
    }

    /**
     *   node                     x
     *  /   \     左旋转         /  \
     * T1   x   --------->   node   T3
     *     / \              /   \
     *    T2 T3            T1   T2
     *
     * @param node 需要左旋转的节点
     * @return 左旋转后的新的根节点 x
     */
    private Node leftRotate(Node node) {

        Node x = node.right;

        node.right = x.left;
        x.left = node;

        // x 节点的颜色需要和原来根节点的颜色保持一致
        x.color = node.color;
        // 2-3树3节点左侧的节点设置为红色，表示需要融合的元素设置为红色
        node.color = RED;

        return x;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        // 最终根节点为黑色节点
        root.color = BLACK;
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            // 默认插入红色节点
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 维护红黑树性质的逻辑链条三步骤
        // 1、左旋转情况
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 2、右旋转情况
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 3、右旋转情况
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}


