class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int ans = Integer.MAX_VALUE;
        
        int left = 0;
        int currentSum = 0;
        int currentMin = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            while (currentSum > target) {
                currentSum -= arr[left++];
            }
            
            if (currentSum == target) {
                int len = right - left + 1;
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + minLen[left - 1]);
                }
                currentMin = Math.min(currentMin, len);
            }
            
            minLen[right] = currentMin;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}