import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] pondSizes(int[][] land) {
        boolean[][] marked = new boolean[land.length][land[0].length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 0 && !marked[i][j]) {
                    list.add(pondSizes(i, j, land, marked));
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        Arrays.sort(result);
        return result;
    }

    public int pondSizes(int row, int column, int[][] land, boolean[][] marked) {
        if (row < 0 || row == land.length) {
            return 0;
        }
        if (column < 0 || column == land[0].length) {
            return 0;
        }
        if (marked[row][column] || land[row][column] != 0) {
            return 0;
        }
        marked[row][column] = true;
        int sum = 1;
        sum += pondSizes(row - 1, column - 1, land, marked);
        sum += pondSizes(row - 1, column, land, marked);
        sum += pondSizes(row - 1, column + 1, land, marked);
        sum += pondSizes(row, column - 1, land, marked);
        sum += pondSizes(row, column + 1, land, marked);
        sum += pondSizes(row + 1, column + 1, land, marked);
        sum += pondSizes(row + 1, column, land, marked);
        sum += pondSizes(row + 1, column - 1, land, marked);
        return sum;
    }
}