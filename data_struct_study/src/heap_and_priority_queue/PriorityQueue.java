package heap_and_priority_queue;

import queue.Queue;

/**
 * 用最大堆实现的优先队列
 *
 * 在 100 万个元素中选取前 100 个元素？
 *
 * 使用优先队列，维护当前看到的前 100 个元素，需要使用最小堆，也可以使用最大堆（将常规的优先级定义取反即可）
 *
 * 使用 TreeMap 求频次
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
