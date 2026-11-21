import java.util.*;
class Solution {
    private List<Integer>[] adj;
    private int[] ans;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        dfs(0, -1);
        return ans;
    }

    private void dfs(int u, int p) {
        int c = s.charAt(u) - 'a';
        int prev = lastSeen[c];
        int newParent = (prev != -1) ? prev : p;

        lastSeen[c] = u;
        for (int v : adj[u]) {
            dfs(v, u);
        }
        lastSeen[c] = prev;

        ans[u] += 1;
        if (newParent != -1) {
            ans[newParent] += ans[u];
        }
    }
}