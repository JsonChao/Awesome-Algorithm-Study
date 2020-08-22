package stack_problem;


import java.util.Stack;

/**
 * 最小值栈
 */
public class Solution155 {

    private Stack<Integer> mDataStack;
    private Stack<Integer> mMinStack;
    private int min;

    public Solution155() {
        mDataStack = new Stack<>();
        mMinStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int e) {
        mDataStack.push(e);
        min = Math.min(min, e);
        mMinStack.push(min);
    }

    public void pop() {
        mDataStack.pop();
        mMinStack.pop();
        min = mMinStack.isEmpty() ? Integer.MAX_VALUE : mMinStack.peek();
    }

    public int getMin() {
        return mMinStack.peek();
    }

    public int top() {
        return mDataStack.peek();
    }

}
