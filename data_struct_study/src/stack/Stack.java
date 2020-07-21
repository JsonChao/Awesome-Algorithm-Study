package stack;


/**
 * Stack 的抽象接口化
 * @param <E>
 */
public interface Stack<E> {

    /**
     * 入栈
     *
     * @param e 入栈的元素
     */
    void push(E e);

    /**
     * 出栈
     *
     * @return e 出栈的元素
     */
    E pop();

    /**
     * 查看栈顶的元素
     *
     * @return e 栈顶的元素
     */
    E peek();

    /**
     * 获取栈元素的大小
     *
     * @return int 栈元素的大小
     */
    int getSize();

    /**
     * 当前栈是否为空
     *
     * @return boolean 栈是否为空
     */
    boolean isEmpty();

}
