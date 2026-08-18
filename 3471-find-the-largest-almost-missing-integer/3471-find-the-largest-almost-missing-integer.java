import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];

        for (int i = 0; i <= n - k; i++) {
            boolean[] seen = new boolean[51];
            for (int j = i; j < i + k; j++) {
                seen[nums[j]] = true;
            }
            for (int v = 0; v <= 50; v++) {
                if (seen[v]) {
                    count[v]++;
                }
            }
        }

        int ans = -1;
        for (int v = 50; v >= 0; v--) {
            if (count[v] == 1) {
                return v;
            }
        }

        return -1;
    }
}