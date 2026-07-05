import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        int[][] maxScore = new int[n][n];
        int[][] pathCount = new int[n][n];

        pathCount[n - 1][n - 1] = 1;

        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};

        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (board.get(r).charAt(c) == 'X' || (r == n - 1 && c == n - 1)) {
                    continue;
                }

                int currentVal = 0;
                char ch = board.get(r).charAt(c);
                if (ch >= '1' && ch <= '9') {
                    currentVal = ch - '0';
                }

                int bestScore = -1;
                int bestCount = 0;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < n && nc < n && pathCount[nr][nc] > 0) {
                        if (maxScore[nr][nc] > bestScore) {
                            bestScore = maxScore[nr][nc];
                            bestCount = pathCount[nr][nc];
                        } else if (maxScore[nr][nc] == bestScore) {
                            bestCount = (bestCount + pathCount[nr][nc]) % MOD;
                        }
                    }
                }

                if (bestScore != -1) {
                    maxScore[r][c] = bestScore + currentVal;
                    pathCount[r][c] = bestCount;
                }
            }
        }

        return new int[]{maxScore[0][0], pathCount[0][0]};
    }
}