import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int left = 0;
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        List<Integer> result = new ArrayList<>();
        while (left <= right && top <= bottom) {
            if (left == right && top == bottom) {
                result.add(matrix[top][left]);
                break;
            } else if (left == right) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][left]);
                }
                break;
            } else if (top == bottom) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                break;
            }

            for (int i = left; i < right; i++) {
                result.add(matrix[top][i]);
            }
            for (int i = top; i < bottom; i++) {
                result.add(matrix[i][right]);
            }
            for (int i = right; i > left; i--) {
                result.add(matrix[bottom][i]);
            }
            for (int i = bottom; i > top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }
}