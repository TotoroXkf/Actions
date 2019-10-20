import java.util.LinkedList;

public class TwoStackQueue {
    private LinkedList<Integer> pushStack = new LinkedList<>();
    private LinkedList<Integer> popStack = new LinkedList<>();

    private void pushToStack(LinkedList<Integer> from, LinkedList<Integer> to) {
        to.clear();
        while (!from.isEmpty()) {
            int value = from.removeLast();
            to.addLast(value);
        }
    }

    public void add(int value) {
        pushStack.addLast(value);
    }

    public Integer poll() {
        pushToStack(pushStack, popStack);
        if (popStack.isEmpty()) {
            return null;
        }
        int result = popStack.removeLast();
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
