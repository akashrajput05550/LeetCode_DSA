class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int len = j - i + 1;
                if (len >= l && len <= r && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                    found = true;
                }
                if (len > r) {
                    break;
                }
            }
        }

        return found ? minSum : -1;
    }
}