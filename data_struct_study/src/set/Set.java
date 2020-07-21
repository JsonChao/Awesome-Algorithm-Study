package set;

public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean isContains(E e);

    int getSize();

    boolean isEmpty();

}
