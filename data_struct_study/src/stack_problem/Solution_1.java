package stack_problem;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 *
 * 1、in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。
 * 一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，
 * 需要先进入 out 栈，此时元素出栈顺序再一次被反转，因此出栈顺序
 * 就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。
 * 2、再s1中写入，要输出时，将所有s1数据移到s2，然后只要s2数据不为空，
 * 就可以一直取出，一旦为空，则再将s1数据移到s2中即可。
 */
public class Solution_1 {

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(int node) {
        in.push(node);
    }

    public int pop() throws Exception {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());

        if (out.isEmpty())
            throw new Exception("queue is empty");

        return out.pop();
    }
}
