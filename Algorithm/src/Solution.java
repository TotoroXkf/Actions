import java.util.LinkedList;

class Solution {
    public int hanoiProblem(int leave) {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> mid = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        int count = 0;
        for (int i = leave; i > 0; i--) {
            left.push(i);
        }
        if (left.isEmpty()) {
            return count;
        }
        while (right.size() != leave) {
            if ((mid.isEmpty() && !left.isEmpty()) || (!mid.isEmpty() && !left.isEmpty() && mid.peek() > left.peek())) {
                int value = left.pop();
                System.out.println("move left " + value + " to mid");
                count++;
                mid.push(value);
            } else {
                int value = mid.pop();
                System.out.println("move mid " + value + " to left");
                count++;
                left.push(value);
            }
            if ((right.isEmpty() && !mid.isEmpty()) || (!right.isEmpty() && !mid.isEmpty() && right.peek() > mid.peek())) {
                int value = mid.pop();
                System.out.println("move mid " + value + " to right");
                count++;
                right.push(value);
            } else {
                int value = right.pop();
                System.out.println("move right " + value + " to mid");
                count++;
                mid.push(value);
            }
        }
        return count;
    }
}