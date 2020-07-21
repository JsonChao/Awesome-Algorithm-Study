package LinkedList;

import stack.Stack;

public class LinkedListStack<E> implements Stack<E> {


    private LinkedList<E> list = new LinkedList<E>();

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return " LinkedListStack: TOP " +
                list;
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();

        System.out.println(stack);

        stack.peek();

        System.out.println(stack);
    }
}
