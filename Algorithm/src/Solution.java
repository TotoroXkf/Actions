import java.util.LinkedList;

class Solution {
    /**
     * 比较好的一道题
     * 这个题的关键就在于，找到当前位置左边第一个比它小的值和右边第一个比它小的值的位置
     * 然后就可以计算出来当前位置的围成面积
     * 整体遍历之后就可以得出最大值
     */
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        // 记录第一个最小值的位置
        int[] leftFirstMinIndex = new int[heights.length];
        int[] rightFirstMinIndex = new int[heights.length];
        // 默认左右两边无最小值的位置，方便计算
        for (int i = 0; i < heights.length; i++) {
            leftFirstMinIndex[i] = -1;
            rightFirstMinIndex[i] = heights.length;
        }
        for (int i = 0; i < heights.length; i++) {
            // 使用栈结构维持升序，这样栈顶就代表了左边遇到的可用最大值。和当前值逐一比较，出栈，就可以快速的找到左边的第一个小于当前值的坐标
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int index = stack.pop();
                // 弹出一个位置下标，比当前元素大，说明当前位置右边第一个最小值就是当前值
                rightFirstMinIndex[index] = i;
            }
            if (!stack.isEmpty()) {
                // 找到左边第一个小于的值，记录下标
                leftFirstMinIndex[i] = stack.peek();
            }
            stack.push(i);
        }
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            // 计算面积
            int area = (rightFirstMinIndex[i] - leftFirstMinIndex[i] - 1) * heights[i];
            max = Math.max(area, max);
        }
        return max;
    }
}