import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multCount[f] += freq[m];
            }
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long rem = budget;
        long ans = 0;

        for (int[] item : items) {
            long price = item[1];
            if (price >= 2 * minPrice) break;
            long canFree = multCount[item[0]] - 1;
            if (canFree <= 0) continue;

            long buy = Math.min(canFree, rem / price);
            ans += buy * 2;
            rem -= buy * price;
            if (rem < price) break;
        }

        ans += rem / minPrice;
        return (int) ans;
    }
}