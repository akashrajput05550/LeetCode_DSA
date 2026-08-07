class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (temp % 2 == 0) { c2++; temp /= 2; }
        while (temp % 3 == 0) { c3++; temp /= 3; }
        while (temp % 5 == 0) { c5++; temp /= 5; }
        while (temp % 7 == 0) { c7++; temp /= 7; }
        if (temp > 1) return "-1";

        int[][] dp = new int[60][40];
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 40; j++) {
                dp[i][j] = 1000000;
            }
        }
        dp[0][0] = 0;

        int[] p2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
        int[] p3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
        int[] p5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        int[] p7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 40; j++) {
                if (i == 0 && j == 0) continue;
                for (int d = 2; d <= 9; d++) {
                    if (d == 5 || d == 7) continue;
                    int prevI = Math.max(0, i - p2[d]);
                    int prevJ = Math.max(0, j - p3[d]);
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[prevI][prevJ]);
                }
            }
        }

        int N = num.length();
        int firstZero = N;
        for (int i = 0; i < N; i++) {
            if (num.charAt(i) == '0') {
                firstZero = i;
                break;
            }
        }

        int[] pref2 = new int[N + 1];
        int[] pref3 = new int[N + 1];
        int[] pref5 = new int[N + 1];
        int[] pref7 = new int[N + 1];

        pref2[0] = c2;
        pref3[0] = c3;
        pref5[0] = c5;
        pref7[0] = c7;

        for (int i = 0; i < firstZero; i++) {
            int d = num.charAt(i) - '0';
            pref2[i + 1] = Math.max(0, pref2[i] - p2[d]);
            pref3[i + 1] = Math.max(0, pref3[i] - p3[d]);
            pref5[i + 1] = Math.max(0, pref5[i] - p5[d]);
            pref7[i + 1] = Math.max(0, pref7[i] - p7[d]);
        }

        if (firstZero == N && pref2[N] == 0 && pref3[N] == 0 && pref5[N] == 0 && pref7[N] == 0) {
            return num;
        }

        for (int i = Math.min(N - 1, firstZero); i >= 0; i--) {
            int startD = (num.charAt(i) - '0') + 1;
            for (int d = startD; d <= 9; d++) {
                int rc2 = Math.max(0, pref2[i] - p2[d]);
                int rc3 = Math.max(0, pref3[i] - p3[d]);
                int rc5 = Math.max(0, pref5[i] - p5[d]);
                int rc7 = Math.max(0, pref7[i] - p7[d]);
                int remLen = N - 1 - i;
                int needed = rc5 + rc7 + dp[rc2][rc3];
                if (remLen >= needed) {
                    return num.substring(0, i) + d + fill(remLen, rc2, rc3, rc5, rc7, dp, p2, p3, p5, p7);
                }
            }
        }

        int minL = c5 + c7 + dp[c2][c3];
        int targetL = Math.max(N + 1, minL);
        return fill(targetL, c2, c3, c5, c7, dp, p2, p3, p5, p7);
    }

    private String fill(int remLen, int c2, int c3, int c5, int c7, int[][] dp, int[] p2, int[] p3, int[] p5, int[] p7) {
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < remLen; pos++) {
            for (int d = 1; d <= 9; d++) {
                int nc2 = Math.max(0, c2 - p2[d]);
                int nc3 = Math.max(0, c3 - p3[d]);
                int nc5 = Math.max(0, c5 - p5[d]);
                int nc7 = Math.max(0, c7 - p7[d]);
                int needed = nc5 + nc7 + dp[nc2][nc3];
                if (remLen - 1 - pos >= needed) {
                    sb.append(d);
                    c2 = nc2;
                    c3 = nc3;
                    c5 = nc5;
                    c7 = nc7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}