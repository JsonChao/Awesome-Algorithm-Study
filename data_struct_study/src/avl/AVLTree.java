package avl;

import java.util.ArrayList;


/**
 * AVL 的自平衡机制：
 *      1、左旋转
 *      2、右旋转
 *
 * 在什么时候维护平衡？
 *      加入节点后，沿着节点向上维护平衡性。
 *
 * 时间复杂度 O(logn)
 *
 * AVL 优化：在维护每一个节点之前，都需要对 AVL 的高度进行重新的计算，
 * 但是如果我们重新计算出的这个节点的高度与原先的高度相等的话，对于这个
 * 节点的祖先节点就不再需要维护平衡的操作了。这是因为这个节点的高度和原先
 * 一样，从它的父亲节点和祖先节点来看，它的子树的高度并没有发送变化，
 * 所以不需要维护平衡了。
 *
 * AVL 树的局限性：红黑树的旋转操作相对更少，性能更优一些。
 *
 * @param <K> key
 * @param <V> value
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    private int getHeight(Node node) {

        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactory(Node node) {

        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBSTTree() {

        ArrayList<K> keys = new ArrayList<>();
        inOrder(keys, root);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {

        if (node == null) {
            return true;
        }

        int balanceFactory = getBalanceFactory(node);
        if (Math.abs(balanceFactory) > 1) {
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);
    }

    /**
     * BST 中序遍历后的数据时升序的
     */
    private void inOrder(ArrayList<K> keys, Node node) {

        if (node == null) {
            return;
        }

        inOrder(keys, node.left);
        keys.add(node.key);
        inOrder(keys, node.right);
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 1、更新当前节点的高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 2、记录不平衡的因子（如果有）
        int balanceFactory = getBalanceFactory(node);

        // 3、维护平衡
        // LL，为保持平衡，进行右旋转
        if (balanceFactory > 1 && getBalanceFactory(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR，为保持平衡，进行左旋转
        if (balanceFactory < - 1 && getBalanceFactory(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR，为保持平衡，先对 node.left 进行左旋转转换为 LL，再对 node 进行右旋转
        if (balanceFactory > 1 && getBalanceFactory(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL，为保持平衡，先对 node.right 进行右旋转转换为 RR，再对 node 进行左旋转
        if (balanceFactory < -1 && getBalanceFactory(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * RR，为保持平衡，进行左旋转.
     * 对节点 y 进行向左旋转操作，返回旋转后新的根节点 x。
     *    y                             x
     *  /  \                          /   \
     * T1   x      向左旋转 (y)       y     z
     *     / \   - - - - - - - ->   / \   / \
     *   T2  z                     T1 T2 T3 T4
     *      / \
     *     T3 T4
     *
     * @param y 不平衡节点
     * @return 新的平衡节点 x
     */
    private Node leftRotate(Node y) {

        Node x = y.right;
        Node T2 = x.left;

        // 左旋转
        x.left = y;
        y.right = T2;

        // 先后更新 y 和 x 的高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * LL，为保持平衡，进行右旋转。
     * 对节点 y 进行向右旋转操作，返回旋转后新的根节点 x。
     *
     *         y                              x
     *        / \                           /   \
     *       x   T4     向右旋转 (y)        z     y
     *      / \       - - - - - - - ->    / \   / \
     *     z   T3                       T1  T2 T3 T4
     *    / \
     *  T1   T2
     * @param y 不平衡节点
     * @return 新的平衡节点 x
     */
    private Node rightRotate(Node y) {

        Node x = y.left;
        Node T3 = x.right;

        // 右旋转
        x.right = y;
        y.left = T3;

        // 先后更新 y 和 x 的高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
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

        if(node == null)
            return null;

        Node retNode;
        if ( key.compareTo(node.key) < 0) {
            node.left = remove(node.left , key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            retNode = node;
        } else {   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else if (node.right == null) {
                // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            } else {
                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
//                successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        // 1、更新当前节点的高度
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 2、记录不平衡的因子（如果有）
        int balanceFactory = getBalanceFactory(retNode);

        // 3、维护平衡
        // LL，为保持平衡，进行右旋转
        if (balanceFactory > 1 && getBalanceFactory(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR，为保持平衡，进行左旋转
        if (balanceFactory < - 1 && getBalanceFactory(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR，为保持平衡，先对 node.left 进行左旋转转换为 LL，再对 node 进行右旋转
        if (balanceFactory > 1 && getBalanceFactory(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL，为保持平衡，先对 node.right 进行右旋转转换为 RR，再对 node 进行左旋转
        if (balanceFactory < -1 && getBalanceFactory(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBSTTree());
            System.out.println("is Balanced : " + map.isBalance());

            for(String word: words){
                map.remove(word);
                if(!map.isBSTTree() || !map.isBalance())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }
}
