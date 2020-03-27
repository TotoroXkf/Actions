class Solution {
    public int waysToStep(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            if (i == 2) {
                dp[i] = dp[1] + 1;
            } else if (i == 3) {
                dp[i] = dp[i - 1] + dp[i - 2] + 1;
            } else {
                dp[i] = ((dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007);
            }
        }
        return (int) dp[n];
    }
}