import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(10);
        stack.push(20);
        stack.push(2);
        stack.pop();
        stack.push(100);
        stack.pop();
        stack.pop();
        stack.push(-1);
        stack.push(20);
        stack.push(9);
        stack.pop();
        System.out.println(stack.getMin());
    }
}