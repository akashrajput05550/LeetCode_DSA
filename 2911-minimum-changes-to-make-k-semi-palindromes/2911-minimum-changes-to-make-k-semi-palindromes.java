class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                int minChanges = Integer.MAX_VALUE;
                
                for (int d = 1; d <= len / 2; d++) {
                    if (len % d == 0) {
                        int currentChanges = 0;
                        for (int offset = 0; offset < d; offset++) {
                            int left = i + offset;
                            int right = j - d + 1 + offset;
                            while (left < right) {
                                if (s.charAt(left) != s.charAt(right)) {
                                    currentChanges++;
                                }
                                left += d;
                                right -= d;
                            }
                        }
                        minChanges = Math.min(minChanges, currentChanges);
                    }
                }
                cost[i][j] = minChanges;
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = 999999;
            }
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = p * 2; i <= n; i++) {
                for (int j = (p - 1) * 2; j <= i - 2; j++) {
                    if (dp[p - 1][j] != 999999) {
                        dp[p][i] = Math.min(dp[p][i], dp[p - 1][j] + cost[j][i - 1]);
                    }
                }
            }
        }
        return dp[k][n];
    }
}