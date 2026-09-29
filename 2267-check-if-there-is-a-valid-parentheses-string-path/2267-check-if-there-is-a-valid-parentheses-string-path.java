class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        memo = new Boolean[m][n][(m + n + 1) / 2 + 1];
        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int open) {
        open += (grid[r][c] == '(') ? 1 : -1;

        if (open < 0 || open > (m - r + n - c - 1)) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean res = false;
        if (r + 1 < m) {
            res = dfs(grid, r + 1, c, open);
        }
        if (!res && c + 1 < n) {
            res = dfs(grid, r, c + 1, open);
        }

        return memo[r][c][open] = res;
    }
}