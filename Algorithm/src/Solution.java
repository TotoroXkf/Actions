import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer[]> findTwoSideMInValuePosition(int[] array) {
        ArrayList<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            result.add(new Integer[]{-1, -1});
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                int index = stack.pop();
                result.get(index)[1] = i;
            }
            if (!stack.isEmpty()) {
                result.get(i)[0] = stack.peek();
            }
            stack.push(i);
        }
        return result;
    }
}