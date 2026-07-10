import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(i -> nums[i]));
        
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[idx[i]] = i;
        }
        
        int[][] st = new int[n][18];
        int r = 0;
        for (int i = 0; i < n; i++) {
            r = Math.max(r, i);
            while (r + 1 < n && nums[idx[r + 1]] - nums[idx[i]] <= maxDiff) {
                r++;
            }
            st[i][0] = r;
        }
        
        for (int j = 1; j < 18; j++) {
            for (int i = 0; i < n; i++) {
                st[i][j] = st[st[i][j - 1]][j - 1];
            }
        }
        
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = pos[queries[q][0]];
            int v = pos[queries[q][1]];
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            
            int steps = 0;
            int curr = u;
            for (int j = 17; j >= 0; j--) {
                if (st[curr][j] < v) {
                    curr = st[curr][j];
                    steps += (1 << j);
                }
            }
            
            if (st[curr][0] >= v) {
                ans[q] = steps + 1;
            } else {
                ans[q] = -1;
            }
        }
        
        return ans;
    }
}