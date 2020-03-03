import java.util.LinkedList;

class Solution {
    /**
     * 柱状图中最大的矩形 问题的延伸问题
     * 对于一个矩形，假设只有一行
     * [1,0,1,0,0]
     * 可以直接把他看成一个移位数组，，然后得到答案
     * 两行的话
     * [1,0,1,0,0]
     * [0,1,1,0,1]
     * 这个时候从底下网上看，就可以得到
     * [0,1,2,0,1]
     * 实际上，当前行如果为0，就不要这个位置了。如果为1，就选择继承上方的值即可
     * 这样从上到下逐步的将值叠加，遇到0丢弃，遇到1继承，每一行都做相同的处理，再传递给函数求值
     * 最后记录最大值即可
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] line = new int[matrix[0].length];
        // 第一行处理
        for (int i = 0; i < matrix[0].length; i++) {
            line[i] = matrix[0][i] - '0';
        }
        int max = largestRectangleArea(line);
        for (int i = 1; i < matrix.length; i++) {
            // 前一行的值
            char[] preLine = matrix[i - 1];
            // 当前行的值
            char[] currentLine = matrix[i];
            for (int j = 0; j < currentLine.length; j++) {
                // 遇到0直接丢弃
                if (currentLine[j] - '0' == 0) {
                    line[j] = 0;
                    continue;
                }
                // 遇到1，继承之前位置的值
                int value = 1 + (preLine[j] - '0');
                line[j] = value;
                currentLine[j] = (char) (value + '0');
            }
            // 比较新结果
            max = Math.max(largestRectangleArea(line), max);
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] leftFirstMinIndex = new int[heights.length];
        int[] rightFirstMinIndex = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            leftFirstMinIndex[i] = -1;
            rightFirstMinIndex[i] = heights.length;
        }
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int index = stack.pop();
                rightFirstMinIndex[index] = i;
            }
            if (!stack.isEmpty()) {
                leftFirstMinIndex[i] = stack.peek();
            }
            stack.push(i);
        }
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = (rightFirstMinIndex[i] - leftFirstMinIndex[i] - 1) * heights[i];
            max = Math.max(area, max);
        }
        return max;
    }
}