package queue;

public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    boolean isEmpty();

    int getSize();

    E getFront();
}
