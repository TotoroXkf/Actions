import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public void reserve(LinkedList<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int value = removeAndGetBottomValue(stack);
        reserve(stack);
        stack.push(value);
    }

    private int removeAndGetBottomValue(LinkedList<Integer> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        }
        int value = stack.pop();
        int result = removeAndGetBottomValue(stack);
        stack.push(value);
        return result;
    }
}