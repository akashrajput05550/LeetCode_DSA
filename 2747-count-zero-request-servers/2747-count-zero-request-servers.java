import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int k = logs.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        int[] count = new int[n + 1];
        int activeServers = 0;
        int left = 0;
        int right = 0;
        int[] ans = new int[m];
        for (int[] query : sortedQueries) {
            int endTime = query[0];
            int startTime = Math.max(0, endTime - x);
            int originalIndex = query[1];
            while (right < k && logs[right][1] <= endTime) {
                int server = logs[right][0];
                if (count[server] == 0) {
                    activeServers++;
                }
                count[server]++;
                right++;
            }
            while (left < k && logs[left][1] < startTime) {
                int server = logs[left][0];
                count[server]--;
                if (count[server] == 0) {
                    activeServers--;
                }
                left++;
            }
            ans[originalIndex] = n - activeServers;
        }
        return ans;
    }
}