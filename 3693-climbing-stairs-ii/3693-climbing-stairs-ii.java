class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1] + 1;
            if (i >= 2) {
                min = Math.min(min, dp[i - 2] + 4);
            }
            if (i >= 3) {
                min = Math.min(min, dp[i - 3] + 9);
            }
            dp[i] = min + costs[i - 1];
        }
        
        return dp[n];
    }
}