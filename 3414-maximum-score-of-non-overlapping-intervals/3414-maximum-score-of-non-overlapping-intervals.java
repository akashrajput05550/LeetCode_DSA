import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }

        static State better(State a, State b) {
            if (a.score > b.score) return a;
            if (b.score > a.score) return b;
            int lenA = a.indices.size();
            int lenB = b.indices.size();
            int minLen = Math.min(lenA, lenB);
            for (int i = 0; i < minLen; i++) {
                if (!a.indices.get(i).equals(b.indices.get(i))) {
                    return a.indices.get(i) < b.indices.get(i) ? a : b;
                }
            }
            return lenA <= lenB ? a : b;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> it = intervalsList.get(i);
            intervals[i] = new Interval(it.get(0), it.get(1), it.get(2), i);
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a.r, b.r));

        int[] rValues = new int[n];
        for (int i = 0; i < n; i++) {
            rValues[i] = intervals[i].r;
        }

        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval cur = intervals[i - 1];
            int prevIdx = binarySearch(rValues, cur.l);

            for (int k = 0; k <= 4; k++) {
                dp[i][k] = dp[i - 1][k];
            }

            for (int k = 1; k <= 4; k++) {
                State prevState = dp[prevIdx][k - 1];
                if (k > 1 && prevState.indices.isEmpty()) {
                    continue;
                }

                long newScore = prevState.score + cur.weight;
                List<Integer> newIndices = new ArrayList<>(prevState.indices);
                newIndices.add(cur.id);
                Collections.sort(newIndices);

                State candidate = new State(newScore, newIndices);
                dp[i][k] = State.better(dp[i][k], candidate);
            }
        }

        State best = new State(-1, new ArrayList<>());
        for (int k = 1; k <= 4; k++) {
            best = State.better(best, dp[n][k]);
        }

        int[] result = new int[best.indices.size()];
        for (int i = 0; i < best.indices.size(); i++) {
            result[i] = best.indices.get(i);
        }
        return result;
    }

    private int binarySearch(int[] rValues, int target) {
        int low = 0, high = rValues.length - 1;
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (rValues[mid] < target) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}