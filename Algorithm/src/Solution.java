import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int index = Arrays.binarySearch(intervals, newInterval, Comparator.comparingInt(o -> o[0]));
        return null;
    }

    private void binarySearch() {

    }

    public static void main(String[] args) {
        int[][] data = new int[][]{
                new int[]{1, 2},
                new int[]{3, 5},
        };
        int[] newInterval = new int[]{2, 3};
        new Solution().insert(data, newInterval);
    }
}