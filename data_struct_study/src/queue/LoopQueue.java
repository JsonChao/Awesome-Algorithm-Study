package queue;


/**
 * 循环队列：
 *      采用队尾和队首两个指针：front、tail，目的是将普通队列中的 出队
 *      时间复杂度降为 O（1），省去复制数组的操作。
 *
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 队列为空时，front 与 tail 都指向同一处
     *
     * @return 队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {

        // 队尾位置 + 1 == 堆首 时，循环对面为满，空出一个空间是为了区分开 判断队列是空还是满的情况。
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        // 正常的入队操作
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 将 data 中 front ~ tail 的元素复制到 newData 中的 0 ~ size - 1 处。
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {

        if (isEmpty()) {
            throw new IllegalArgumentException("no data!");
        }

        // 预先取出要返回的出队元素
        E e = data[front];

        // 设置出队元素为空，避免产生游离的对象
        data[front] = null;

        // 循环队列的好处就在于可以 利用 front 后移一位来替代 普通队列中的遍历复制操作。
        front = (front + 1) % data.length;
        size--;

        // 缩容时注意判断要缩容的容积不能为 0
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return e;
    }

    @Override
    public E getFront() {

        if (isEmpty()) {
            throw new IllegalArgumentException("no data!");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue size is %d\n", size));
        stringBuilder.append("Front [ ");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[(front + i) % data.length]);
            if (i != (size - 1) % data.length) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(" ] Tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Object> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);

            if (i % 3 == 2) {
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
