import java.util.Arrays;

class Solution {
    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[][][] dp = new int[maxVal + 1][maxVal + 1][2];
        dp[0][0][0] = 1;

        int MOD = 1_000_000_007;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int[][][] nextDp = new int[maxVal + 1][maxVal + 1][2];

            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    for (int state = 0; state < 2; state++) {
                        int count = dp[g1][g2][state];
                        if (count == 0) continue;

                        nextDp[g1][g2][state] = (nextDp[g1][g2][state] + count) % MOD;

                        int n1 = (g1 == 0) ? num : gcd(g1, num);
                        int ns1 = (g1 == 0) ? 1 : state;
                        nextDp[n1][g2][ns1] = (nextDp[n1][g2][ns1] + count) % MOD;

                        int n2 = (g2 == 0) ? num : gcd(g2, num);
                        nextDp[g1][n2][state] = (nextDp[g1][n2][state] + count) % MOD;
                    }
                }
            }
            dp = nextDp;
        }

        long ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g][1]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}