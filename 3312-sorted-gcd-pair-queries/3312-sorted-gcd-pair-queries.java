import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        long[] count = new long[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        long[] gcdCount = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long totalMultiples = 0;
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                totalMultiples += count[multiple];
            }
            
            long totalPairs = (totalMultiples * (totalMultiples - 1)) / 2;
            
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                totalPairs -= gcdCount[multiple];
            }
            gcdCount[g] = totalPairs;
        }

        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdCount[i];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            int low = 1, high = maxVal, res = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > target) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = res;
        }

        return ans;
    }
}