import java.util.Arrays;

public class Solution {
    public int countEval(String s, int target) {
        int[][][] dp = new int[s.length()][s.length() + 1][2];
        for (int[][] matrix : dp) {
            for (int[] ints : matrix) {
                Arrays.fill(ints, -1);
            }
        }
        char[] chars = s.toCharArray();
        return countEval(chars, 0, s.length(), dp, target);
    }

    public int countEval(char[] chars, int start, int end, int[][][] dp, int target) {
        // 单个数字的dp值是可以直接得到的，就是比较和target是不是相同
        dp[start][start + 1][target] = ((chars[start] - '0') == target) ? 1 : 0;
        // 存在dp值，直接复用
        if (dp[start][end][target] != -1) {
            return dp[start][end][target];
        }
        int result = 0;
        for (int i = start + 2; i < end; i += 2) {
            // 从开始之后的第二个数字开始
            char symbol = chars[i - 1];
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    // 遍历0和1的4个组合，用符号计算，得到值合适的时候递归左右两边
                    if (compute(j, k, symbol) == target) {
                        result += (countEval(chars, start, i - 1, dp, j) * countEval(chars, i, end, dp, k));
                    }
                }
            }
        }
        // 将结果值记录下来
        dp[start][end][target] = result;
        return result;
    }

    private int compute(int a, int b, char symbol) {
        switch (symbol) {
            case '|':
                return a | b;
            case '&':
                return a & b;
            case '^':
                return a ^ b;
        }
        return -1;
    }
}