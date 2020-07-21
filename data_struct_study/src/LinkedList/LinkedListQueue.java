package LinkedList;

import queue.LoopQueue;
import queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {

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

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {

        // 没有使用 dummyHead 时，入队之前需要处理队列为空的情况
        if (head == null) {
            head = new Node(e);
            tail = head;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {

        if (size == 0) {
            throw new IllegalArgumentException("queue is empty!");
        }

        Node netNode = head;
        head = head.next;
        netNode.next = null;

        // 没有使用 dummyHead 时，出队之后需要处理队列为空的情况
        if (head == null) {
            tail = null;
        }

        size--;

        return netNode.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public E getFront() {

        if (size == 0) {
            throw new IllegalArgumentException("queue is empty！");
        }

        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue size is %d\n", size));
        stringBuilder.append("Front [ ");

        Node netNode = head;
        while (netNode != null) {
            stringBuilder.append(netNode.e).append(" -> ");
            netNode = netNode.next;
        }

        stringBuilder.append(" NULL ] Tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Object> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);

            if (i % 3 == 2) {
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
