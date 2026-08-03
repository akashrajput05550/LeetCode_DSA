class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int maxDiff = Integer.MIN_VALUE;
            int currentSum = 0;

            for (int k = 0; k < 3 && i + k < n; k++) {
                currentSum += stoneValue[i + k];
                maxDiff = Math.max(maxDiff, currentSum - dp[i + k + 1]);
            }

            dp[i] = maxDiff;
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}