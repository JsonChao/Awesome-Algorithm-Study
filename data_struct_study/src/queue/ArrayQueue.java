package queue;

/**
 * 数组队列实现：
 *       其中 enqueue 的时间复杂度为 O（n)，需要使用循环队列改进为 O（1）。
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue() {
        array = new Array<>();
    }

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.deleteFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public E getFront() {
        return array.queryFirst();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue size is %d\n", array.getSize()));
        stringBuilder.append("Front [ ");
        for (int i = 0; i < array.getSize(); i++) {
            stringBuilder.append(array.query(i));
            if (i != array.getSize() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(" ] Tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Object> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);

            if (i % 3 == 2) {
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
