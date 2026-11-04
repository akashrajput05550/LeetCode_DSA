import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;
        long[] cost = new long[m];

        Comparator<Integer> cmp = (a, b) -> {
            if (nums[a] != nums[b]) {
                return Integer.compare(nums[a], nums[b]);
            }
            return Integer.compare(a, b);
        };

        TreeSet<Integer> left = new TreeSet<>(cmp);
        TreeSet<Integer> right = new TreeSet<>(cmp);
        long sumLeft = 0;
        long sumRight = 0;

        for (int i = 0; i < n; i++) {
            if (left.isEmpty() || cmp.compare(i, left.last()) <= 0) {
                left.add(i);
                sumLeft += nums[i];
            } else {
                right.add(i);
                sumRight += nums[i];
            }

            int targetLeft = (i + 1 < x ? i + 2 : x + 1) / 2;
            while (left.size() > targetLeft) {
                int idx = left.pollLast();
                sumLeft -= nums[idx];
                right.add(idx);
                sumRight += nums[idx];
            }
            while (left.size() < targetLeft && !right.isEmpty()) {
                int idx = right.pollFirst();
                sumRight -= nums[idx];
                left.add(idx);
                sumLeft += nums[idx];
            }

            if (i >= x - 1) {
                int start = i - x + 1;
                long median = nums[left.last()];
                cost[start] = ((long) left.size() * median - sumLeft) + (sumRight - (long) right.size() * median);

                if (left.contains(start)) {
                    left.remove(start);
                    sumLeft -= nums[start];
                } else {
                    right.remove(start);
                    sumRight -= nums[start];
                }

                int newTarget = x / 2;
                while (left.size() > newTarget) {
                    int idx = left.pollLast();
                    sumLeft -= nums[idx];
                    right.add(idx);
                    sumRight += nums[idx];
                }
                while (left.size() < newTarget && !right.isEmpty()) {
                    int idx = right.pollFirst();
                    sumRight -= nums[idx];
                    left.add(idx);
                    sumLeft += nums[idx];
                }
            }
        }

        long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);
            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(curr[i], prev[i - x] + cost[i - x]);
                }
            }
            prev = curr;
        }

        return prev[n];
    }
}