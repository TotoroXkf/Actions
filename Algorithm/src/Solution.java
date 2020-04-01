import java.util.Arrays;

class Solution {
    public int waysToChange(int n) {
        int[] coins = new int[]{1, 5, 10, 25};
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                // 动态转移方程
                if (j >= coins[i]) {
                    dp[j] = (dp[j] + dp[j - coins[i]]) % 1000000007;
                }
            }
        }
        return dp[n];
    }
}