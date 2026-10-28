class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] dp = new boolean[m][n][1024];

        int startVal = grid[0][0];
        dp[0][0][startVal] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int val = grid[i][j];

                if (i > 0) {
                    for (int k = 0; k < 1024; k++) {
                        if (dp[i - 1][j][k]) {
                            dp[i][j][k ^ val] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int k = 0; k < 1024; k++) {
                        if (dp[i][j - 1][k]) {
                            dp[i][j][k ^ val] = true;
                        }
                    }
                }
            }
        }

        for (int k = 0; k < 1024; k++) {
            if (dp[m - 1][n - 1][k]) {
                return k;
            }
        }

        return 0;
    }
}