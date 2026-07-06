import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int remaining = 0;
        int currentRight = 0;

        for (int[] interval : intervals) {
            if (interval[1] > currentRight) {
                remaining++;
                currentRight = interval[1];
            }
        }

        return remaining;
    }
}