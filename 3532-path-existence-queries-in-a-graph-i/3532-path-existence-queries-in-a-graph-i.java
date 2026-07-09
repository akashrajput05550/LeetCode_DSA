class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= maxDiff) {
                int root1 = find(i, parent);
                int root2 = find(i + 1, parent);
                if (root1 != root2) {
                    parent[root1] = root2;
                }
            }
        }
        
        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = find(queries[i][0], parent) == find(queries[i][1], parent);
        }
        
        return result;
    }
    
    private int find(int i, int[] parent) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i], parent);
    }
}