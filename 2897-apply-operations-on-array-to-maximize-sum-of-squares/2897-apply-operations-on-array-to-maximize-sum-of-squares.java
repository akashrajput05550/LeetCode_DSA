import java.util.List;
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCount[i]++;
                }
            }
        }
        long ans = 0;
        long MOD = 1_000_000_007L;

        for (int i = 0; i < k; i++) {
            long val = 0;
            for (int bit = 0; bit < 30; bit++) {
                if (bitCount[bit] > 0) {
                    val |= (1L << bit);
                    bitCount[bit]--;
                }
            }
            ans = (ans + (val % MOD) * (val % MOD)) % MOD;
        }

        return (int) ans;
    }
}