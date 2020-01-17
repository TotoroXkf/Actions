class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i;
        for (i = 0; i < intervals.length && newInterval[0] > intervals[i][0]; i++) ;
        int left = i - 1;
        int right = i;
        while (left >= 0 && intervals[left][1] >= newInterval[0]) {
            newInterval[0] = intervals[left][0];
            newInterval[1] = Math.max(intervals[left][1], newInterval[1]);
            left--;
        }
        while (right < intervals.length && (intervals[right][0] == newInterval[0] || intervals[right][0] <= newInterval[1])) {
            newInterval[1] = Math.max(intervals[right][1], newInterval[1]);
            right++;
        }
        int len = left + 1 + 1 + intervals.length - right;
        int[][] result = new int[len][2];
        i = 0;
        int index = 0;
        while (i <= left) {
            result[index++] = intervals[i++];
        }
        result[index++] = newInterval;
        i = right;
        while (i < intervals.length) {
            result[index++] = intervals[i++];
        }
        return result;
    }
}