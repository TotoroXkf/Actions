import java.util.LinkedList;

public class MinStack {
    private LinkedList<Integer> stack = new LinkedList<>();
    private LinkedList<Integer> minStack = new LinkedList<>();

    public void push(int value) {
        if (stack.isEmpty()) {
            stack.push(value);
            minStack.push(value);
            return;
        }
        stack.push(value);
        if (value <= minStack.getLast()) {
            minStack.push(value);
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int popValue = stack.pop();
        if (popValue == minStack.getLast() && !minStack.isEmpty()) {
            minStack.pop();
        }
        return popValue;
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return minStack.getLast();
    }
}
