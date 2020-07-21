package stack;


import array.Array;

/**
 * 栈的一种实现方式：使用动态数组实现栈
 *
 * @param <E> 栈中的元素
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.deleteLast();
    }

    @Override
    public E peek() {
        return array.queryLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack: ");
        builder.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.query(i));
            if (i != array.getSize() - 1) {
                builder.append(",");
            }
        }
        builder.append("] Top");
        return builder.toString();
    }
}
