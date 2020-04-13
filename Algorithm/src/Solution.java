class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int column = 0;
        while (row > -1 && column < matrix[0].length) {
            if (matrix[row][column] == target) {
                return true;
            }
            if (matrix[row][column] < target) {
                column++;
            } else {
                row--;
            }
        }
        return false;
    }
}