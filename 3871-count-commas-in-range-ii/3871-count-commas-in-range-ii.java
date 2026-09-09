class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long threshold = 1000L;
        while (n >= threshold) {
            ans += (n - threshold + 1);
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000L;
        }
        return ans;
    }
}