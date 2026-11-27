class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1500;
        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }
        int[] freeByFactor = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (factorCount[f] == 0) {
                continue;
            }
            int multiples = 0;
            for (int m = f; m <= maxFactor; m += f) {
                multiples += factorCount[m];
            }
            freeByFactor[f] = multiples - 1;
        }

        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int f = item[0];
            int p = item[1];
            minPrice = Math.min(minPrice, p);
            int gain = 1 + freeByFactor[f];
            for (int b = budget; b >= p; b--) {
                dp[b] = Math.max(dp[b], dp[b - p] + gain);
            }
        }

        for (int b = minPrice; b <= budget; b++) {
            dp[b] = Math.max(dp[b], dp[b - minPrice] + 1);
        }

        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
}