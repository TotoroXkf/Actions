import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        int[][] result = new int[intervals.length][2];
        int currentIndex = 0;
        int[] currentInterval = new int[2];
        currentInterval[0] = intervals[0][0];
        currentInterval[1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int value1 = intervals[i][0];
            int value2 = intervals[i][1];
            if (value1 <= currentInterval[1]) {
                currentInterval[1] = Math.max(currentInterval[1], value2);
            } else {
                result[currentIndex++] = currentInterval;
                currentInterval = new int[2];
                currentInterval[0] = intervals[i][0];
                currentInterval[1] = intervals[i][1];
            }
        }
        result[currentIndex++] = currentInterval;
        result = Arrays.copyOf(result, currentIndex);
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                new int[]{1, 3},
                new int[]{8, 10},
                new int[]{2, 6},
                new int[]{15, 18}
        };
        new Solution().merge(intervals);
    }
}