import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        LinkedList<Integer> stack = Utils.newRandomStack(1);
        Utils.println(stack);
        new Solution().sortStack(stack);
        Utils.println(stack);
    }

    public void sortStack(LinkedList<Integer> stack) {
        LinkedList<Integer> helper = new LinkedList<>();
        while (!stack.isEmpty()) {
            int value = stack.pop();
            while (!helper.isEmpty() && value > helper.peek()) {
                stack.push(helper.pop());
            }
            helper.push(value);
        }
        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
    }
}