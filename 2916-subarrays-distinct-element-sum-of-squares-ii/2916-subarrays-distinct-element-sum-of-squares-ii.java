import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    private long[] treeSum;
    private long[] treeSqSum;
    private long[] lazy;

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        int mid = start + (end - start) / 2;
        int left = 2 * node;
        int right = 2 * node + 1;
        long add = lazy[node];

        apply(left, start, mid, add);
        apply(right, mid + 1, end, add);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long add) {
        long len = end - start + 1;
        treeSqSum[node] = (treeSqSum[node] + 2 * add % MOD * treeSum[node] % MOD + add * add % MOD * len % MOD) % MOD;
        treeSum[node] = (treeSum[node] + add * len % MOD) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }

        pushDown(node, start, end);
        int mid = start + (end - start) / 2;

        if (l <= mid) {
            update(2 * node, start, mid, l, r, val);
        }
        if (r > mid) {
            update(2 * node + 1, mid + 1, end, l, r, val);
        }

        treeSum[node] = (treeSum[2 * node] + treeSum[2 * node + 1]) % MOD;
        treeSqSum[node] = (treeSqSum[2 * node] + treeSqSum[2 * node + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        treeSum = new long[4 * n];
        treeSqSum = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastPos = new HashMap<>();
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastPos.getOrDefault(nums[i], -1);
            update(1, 0, n - 1, prev + 1, i, 1);
            totalSum = (totalSum + treeSqSum[1]) % MOD;
            lastPos.put(nums[i], i);
        }

        return (int) totalSum;
    }
}