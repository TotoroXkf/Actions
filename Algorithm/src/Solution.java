import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> result = new ArrayList<>();
        pathWithObstacles(obstacleGrid, result, 0, 0);
        return result;
    }

    public boolean pathWithObstacles(int[][] obstacleGrid, List<List<Integer>> answer, int row, int column) {
        if (column >= obstacleGrid[0].length || row >= obstacleGrid.length) {
            return false;
        }
        if (obstacleGrid[row][column] == 1) {
            return false;
        }
        List<Integer> point = new ArrayList<>();
        point.add(row);
        point.add(column);
        answer.add(point);
        if (row == obstacleGrid.length - 1 && column == obstacleGrid[0].length - 1) {
            return true;
        }
        boolean rightResult = pathWithObstacles(obstacleGrid, answer, row, column + 1);
        if (rightResult) {
            return true;
        }
        boolean bottomResult = pathWithObstacles(obstacleGrid, answer, row + 1, column);
        if (bottomResult) {
            return true;
        }
        obstacleGrid[row][column] = 1;
        answer.remove(answer.size() - 1);
        return false;
    }
}