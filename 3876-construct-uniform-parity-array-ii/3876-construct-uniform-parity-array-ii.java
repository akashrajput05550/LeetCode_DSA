class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        if (n <= 1) {
            return true;
        }

        int minVal = Integer.MAX_VALUE;
        for (int x : nums1) {
            minVal = Math.min(minVal, x);
        }

        if (minVal % 2 != 0) {
            return true;
        }

        for (int x : nums1) {
            if (x % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}