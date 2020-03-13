import java.util.LinkedList;

class SortedStack {
    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public SortedStack() {

    }

    public void push(int val) {
        while (!stack.isEmpty() && stack.peek() < val) {
            temp.push(stack.pop());
        }
        stack.push(val);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
    }

    public int peek() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}