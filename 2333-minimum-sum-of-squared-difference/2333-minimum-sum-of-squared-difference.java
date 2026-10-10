class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        if (maxDiff == 0) {
            return 0;
        }
        
        int[] count = new int[maxDiff + 1];
        for (int i = 0; i < n; i++) {
            count[Math.abs(nums1[i] - nums2[i])]++;
        }
        
        long k = (long) k1 + k2;
        
        for (int diff = maxDiff; diff > 0 && k > 0; diff--) {
            if (count[diff] == 0) {
                continue;
            }
            
            long canMove = Math.min((long) count[diff], k);
            count[diff] -= canMove;
            count[diff - 1] += canMove;
            k -= canMove;
            
            if (count[diff] > 0) {
                break;
            }
        }
        
        long ans = 0;
        for (int diff = 1; diff <= maxDiff; diff++) {
            if (count[diff] > 0) {
                ans += (long) count[diff] * (long) diff * diff;
            }
        }
        
        return ans;
    }
}