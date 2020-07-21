package LinkedList;


/**
 * 为什么链表很重要？
 *      不同于 动态数组、栈、队列的实现：其底层是依托静态数组，靠 resize 解决固定容量问题，
 *      链表是真正的动态数据结构，也是最简单的动态数据结构。
 *      能够帮助我们更深入地理解引用（指针）与递归。
 *      优势：真正的动态，不需要处理固定容量的问题。
 *      逆势：不同于数组其底层的数据是连续分布的，链表底层的数据分布是随机的，
 *      紧靠next（pre）指针连接，因此链表相对于数组丧失了随机访问的能力。
 *
 * 数组和链表的区别？
 *      数组最好被应用于索引有语义的情况，例如 Students[1]
 *      最大的优势：支持动态查询。
 *
 *      链表不能被应用于索引有语义的情况。
 *      最大的优势：动态。
 *
 * 链表的时间复杂度：
 *      增： O(n)
 *      删： O(n)
 *      改： O(n)
 *      查： O(n)
 *
 *      总结：链表不适合去修改，且只适合 增、删、查 链表头的元素，此时时间复杂度为 O(1)。
 */
public class LinkedList<E> {

    /**
     * Node 应该被设置成私有的，用户对此是无感知的。
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
           this(e, null);
        }

        public Node() {
           this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 设立一个 dummyHead（虚拟头节点，即头结点的前一个节点），为了后续编写统一逻辑的方便
    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    /**
     * 获取链表的大小
     *
     * @return 链表的大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return 链表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表指定位置添加元素
     *
     * @param index 指定位置
     * @param e E
     */
    public void add(int index, E e) {

        if ((index < 0 || index > size)) {
            throw new IllegalArgumentException("add failed!");
        }

        // 设立一个 dummyHead（虚拟头节点，即头结点的前一个节），为了后续编写统一逻辑的方便
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

//        Node node = new Node(e);
//        node.next = pre.next;
//        pre.next = node;
        pre.next = new Node(e, pre.next);
        size++;
    }

    /**
     * 在链表头结点添加元素
     *
     * @param e E
     * @return Node
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表最后一个位置添加元素
     *
     * @param e E
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 查询链表中指定节点的元素
     *
     * @param index 指定节点的下标
     * @return 指定节点的元素
     */
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("no node!");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取链表头部的元素
     *
     * @return 链表头部的元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表尾部的元素
     *
     * @return 链表尾部的元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表指定位置的元素
     *
     * @param index 链表指定位置
     * @param e 链表指定位置的元素
     */
    public void set(int index, E e) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("no index!");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 删除 {@index} 处的元素
     *
     * @param index 指定位置
     * @return E
     */
    public E remove(int index) {

        if ((index < 0 || index >= size)) {
            throw new IllegalArgumentException("index is illegal!");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node cur = pre.next;
        pre.next = cur.next;
        cur.next = null;
        size--;

        return cur.e;
    }

    /**
     * 删除链表头部的元素
     *
     * @return 链表头部的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除链表尾部的元素
     *
     * @return 链表尾部的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素e
     *
     * @param e E
     */
    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    /**
     * 判断链表中是否包含元素 E
     *
     * @param e E
     * @return 链表中是否包含元素 E
     */
    public boolean isContains(E e) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList: ");
//        Node cur = dummyHead.next;
//        while (cur != null) {
//            sb.append(cur).append(" -> ");
//            cur = cur.next;
//        }

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            sb.append(cur).append(" -> ");
        }
        sb.append("NULL");
        return sb.toString();
    }
}
