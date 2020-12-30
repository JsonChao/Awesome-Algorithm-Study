package stack_problem;


import java.util.Stack;

/**
 * 使用双栈（数据栈+最小值栈）+min实现最小值栈
 */
public class Solution155 {

    private Stack<Integer> mDataStack;
    private Stack<Integer> mMinStack;
    private int min;

    public Solution155() {
        // 1、创建一个数据栈保存数据，最小值栈的栈顶维护最小值，min变量维护最小值
        mDataStack = new Stack<>();
        mMinStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    // 2、放入元素时保持栈顶的元素为最小值
    public void push(int e) {
        mDataStack.push(e);
        min = Math.min(min, e);
        mMinStack.push(min);
    }

    // 3、弹出元素时保持min记录的为最小值
    public void pop() {
        mDataStack.pop();
        mMinStack.pop();
        min = mMinStack.isEmpty() ? Integer.MAX_VALUE : mMinStack.peek();
    }

    // 4、查看最小值
    public int getMin() {
        return mMinStack.peek();
    }

    // 5、查看栈顶值
    public int top() {
        return mDataStack.peek();
    }

}
