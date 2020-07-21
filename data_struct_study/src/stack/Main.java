package stack;


/**
 * 栈的应用：
 *      1、无处不在的撤销操作
 *      2、系统栈的调用（操作系统）
 *      3、括号匹配（编译器）
 */
public class Main {

    public static void main(String[] args) {

        ArrayStack<Object> stack = new ArrayStack<>();

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
