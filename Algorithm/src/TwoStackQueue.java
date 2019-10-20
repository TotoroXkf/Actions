import java.util.LinkedList;

public class TwoStackQueue {
    private LinkedList<Integer> pushStack = new LinkedList<>();
    private LinkedList<Integer> popStack = new LinkedList<>();

    private void pushToStack(LinkedList<Integer> from, LinkedList<Integer> to) {
        to.clear();
        while (!from.isEmpty()) {
            int value = from.pop();
            to.push(value);
        }
    }

    public void add(int value) {
        pushStack.push(value);
    }

    public Integer poll() {
        pushToStack(pushStack, popStack);
        if (popStack.isEmpty()) {
            return null;
        }
        int result = popStack.pop();
        pushToStack(popStack, pushStack);
        return result;
    }

    public Integer peek() {
        pushToStack(pushStack, popStack);
        if (!popStack.isEmpty()) {
            return popStack.getLast();
        }
        return null;
    }
}
