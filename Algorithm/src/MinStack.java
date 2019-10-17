import java.util.LinkedList;

public class MinStack {
    private LinkedList<Integer> stack = new LinkedList<>();
    private LinkedList<Integer> minStack = new LinkedList<>();

    public void push(int value) {
        if (stack.isEmpty()) {
            stack.addLast(value);
            minStack.addLast(value);
            return;
        }
        stack.addLast(value);
        if (value <= minStack.getLast()) {
            minStack.addLast(value);
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int popValue = stack.removeLast();
        if (popValue == minStack.getLast() && !minStack.isEmpty()) {
            minStack.removeLast();
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
