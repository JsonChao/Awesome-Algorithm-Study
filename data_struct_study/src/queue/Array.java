package queue;

/**
 * 1、实现基本功能：增删改查、各种判断方法等等
 * 2、使用 泛型 让我们的数据结构可以放置 "任何"（不可以是基本
 * 数据类型，只能是类对象，好在每一个基本类型都有一个对应的
 * 包装类，它们之间可以自动进行装箱和拆箱的操作） 的数据类型
 * 3、数组的扩容与缩容
 *
 * 手写时的易错点：
 *          1、注意数组的下标
 *          2、注意 size 与 capacity 的区别
 *
 * 简单的时间复杂度分析：
 *      为什么要用大O，叫做大O(n)？
 *          忽略了常数，实际时间 T = c1 * n + c2
 *      为甚不加上其中每一个常数项，而是要忽略它？
 *          比如说把一个数组中的元素取出来这个操作，很有可能不同的语音基于不同的实现，它实际运行的时间是不等的。
 *          就算转换成机器码，它对应的那个机器码的指令数也有可能是不同的。就算指令数是相同的，同样一个指令在 CPU
 *          的底层，你使用的 CPU 不同，很有可能执行的操作也是不同的。所以，在实际上我们大概能说出来这个 c1
 *          是多少，但是很难准确判断其具体的值。
 *      大O的表示的是渐进时间复杂度，当 n 趋近于无穷大的时候，其算法谁快谁慢。
 *      增：O（n)
 *      删：O（n）
 *      改：已知索引 O（1）；未知索引 O（n）
 *      查：已知索引 O（1）；未知索引 O（n）
 *
 * 均摊时间复杂度分析：
 *      假设 capacity = n，n + 1 次 addLast/removeLast，触发 resize，总共进行 2n + 1 次基本操作
 *      平均来看，每次 addLast/removeLast 操作，进行 2 次基本操作
 *      均摊计算，时间复杂度为 O（1）
 *
 * 复杂度震荡：
 *      当反复先后调用 addLast/removeLast 进行操作时，会不断地进行 扩容/缩容，此时时间复杂度为 O（n）
 *      出现问题的原因：removeLast 时 resize 过于着急
 *      解决方案：Lazy （在这里是 Lazy 缩容）
 *      等到 size == capacity / 4 时，才将 capacity 减半
 *
 * Array 泛型中盛放的是 E 类型的数据
 */
public class Array<E> {

    /**
     * capacity 即为 data.length
     */
    private E[] data;

    /**
     * size 即为 当前数组的大小
     */
    private int size;

    /**
     * 构造函数：创建一个具有 capacity 容量的数组
     * @param capacity 容量大小
     */
    public Array(int capacity) {
        // Object 这里导入了别的 Object 接口(org.omg.CORBA.Object)，
        // 导致浪费了半小时查找错误。。
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 默认构造函数：数组容量大小为 10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组的容量
     * @return 容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 获取数组的大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组最后添加一个元素
     * 时间复杂度：O（1）
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向数组开始添加一个元素
     * 时间复杂度：O（n）
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向数组指定位置添加一个元素
     * 时间复杂度：O（n）
     * 数组的添加操作的总体时间复杂度为 O（n），需要考虑最坏的情况
     */
    public void add(int position, E e) {

        /**
         * 插入的形式只有如下三种：
         *          1、从开始插入元素：data[0]
         *          2、从最后插入元素：data[size]
         *          3、从中间插入元素：data[0~size]
         */
        if ((position < 0 || position > size)) {
            throw new IllegalArgumentException("add failed, position is illegal");
        }

        if (size == data.length) {
            // 数组扩容，Java 中的扩容机制是 1.5 倍
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= position; i--) {
            data[i + 1] = data[i];
        }

        data[position] = e;
        size++;
    }

    /**
     * 扩容/缩容
     * 时间复杂度：O（n）
     * @param capacity
     */
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 查询数组中最后一个位置的元素
     *
     * @return 数组中的元素
     */
    public E queryLast() {
        return query(getSize() - 1);
    }

    /**
     * 查询数组中第一个位置的元素
     *
     * @return 数组中的元素
     */
    public E queryFirst() {
        return query(0);
    }

    /**
     * 查询数组中指定位置的元素
     *
     * @return 数组中的元素
     */
    public E query(int position) {

        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("position is illegal~");
        }

        return data[position];
    }

    /**
     * 修改数组中指定位置的元素
     */
    public void set(int position, E e) {

        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("position is illegal~");
        }

        data[position] = e;
    }

    /**
     * 数组中是否包含某元素
     *
     * @return 是否包含某元素
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中对应元素的下标
     *
     * @return 对应元素的下标
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除数组中最开始的元素
     *
     * @return 被删除的元素
     */
    public E deleteFirst() {
        return delete(0);
    }

    /**
     * 删除数组中最后的元素
     *
     * @return 被删除的元素
     */
    public E deleteLast() {
        return delete(size - 1);
    }

    /**
     * 删除数组中指定的元素
     *
     * @param e 被删除的元素
     * @return 是否删除成功
     */
    public boolean deleteElement(E e) {
        int index = find(e);
        if (index != -1) {
            delete(index);
            return true;
        }
        return false;
    }

    /**
     * 删除数组中指定下标的元素
     *
     * @return 被删除的元素
     */
    public E delete(int position) {

        if ((position < 0 || position >= size)) {
            throw new IllegalArgumentException("position is illegal!");
        }

        E deleteE = data[position];
        for (int i = position + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        // 节省空间
        data[size] = null;

        // 防止复杂度的震荡，同时注意处理 data.length / 2 = 0（即 data.length < 2) 的情况
        if (size == data.length / 4 && data.length / 2 != 0) {
            // 数组的缩容
            resize(data.length / 2);
        }

        return deleteE;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array size is %d, length is %d\n", size, data.length));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
