class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1][0] && p.charAt(i-1) == '*') {
                dp[i][0] = true;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(j - 1) == p.charAt(i - 1) && dp[i - 1][j - 1]) {
                    dp[i][j] = true;
                }
                if (p.charAt(i - 1) == '?' && dp[i - 1][j - 1]) {
                    dp[i][j] = true;
                }
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isMatch("", "");
    }
}