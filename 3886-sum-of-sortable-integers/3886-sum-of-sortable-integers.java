class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            if (isSortable(nums, n, k)) {
                total += k;
            }
        }

        return total;
    }

    private boolean isSortable(int[] nums, int n, int k) {
        int m = n / k;
        int prevMax = 0;

        for (int b = 0; b < m; b++) {
            int start = b * k;
            int drops = 0;
            int pivot = 0;

            for (int i = 0; i < k; i++) {
                int curr = nums[start + i];
                int next = nums[start + (i + 1) % k];
                if (curr > next) {
                    drops++;
                    pivot = (i + 1) % k;
                }
            }

            if (drops > 1) {
                return false;
            }

            int curMin = nums[start + pivot];
            int curMax = nums[start + (pivot + k - 1) % k];

            if (b > 0 && curMin < prevMax) {
                return false;
            }

            prevMax = curMax;
        }

        return true;
    }
}