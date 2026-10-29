import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] inTime, outTime;
    private int timer;
    private int[][] up;
    private int LOG;
    private int[] bit;
    private int N;

    private void add(int idx, int val) {
        for (; idx <= 2 * N; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private int queryBit(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }
        return sum;
    }

    private void dfs(int u, int p) {
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        inTime[u] = ++timer;
        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u);
            }
        }
        outTime[u] = ++timer;
    }

    private boolean isAncestor(int u, int v) {
        return inTime[u] <= inTime[v] && outTime[u] >= outTime[v];
    }

    private int getLca(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;
        for (int i = LOG - 1; i >= 0; i--) {
            if (!isAncestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.N = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        up = new int[n][LOG];
        inTime = new int[n];
        outTime = new int[n];
        timer = 0;

        dfs(0, 0);

        bit = new int[2 * n + 2];
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            add(inTime[i], mask);
            add(outTime[i], mask);
        }

        List<Boolean> ansList = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int change = oldMask ^ newMask;
                if (change != 0) {
                    add(inTime[u], change);
                    add(outTime[u], change);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLca(u, v);
                int maskU = queryBit(inTime[u]);
                int maskV = queryBit(inTime[v]);
                int maskLcaNode = 1 << (chars[lca] - 'a');
                int pathMask = maskU ^ maskV ^ maskLcaNode;
                ansList.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ansList;
    }
}