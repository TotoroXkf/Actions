import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        TwoStackQueue twoStackQueue = new TwoStackQueue();
        twoStackQueue.add(1);
        twoStackQueue.add(2);
        twoStackQueue.add(3);
        System.out.println(twoStackQueue.poll());
        twoStackQueue.add(4);
        System.out.println(twoStackQueue.poll());
        System.out.println("" + twoStackQueue.peek());
    }
}