class Solution {
    static class Node {
        int prod;
        int[] count = new int[5];

        Node() {
            this.prod = 1;
        }
        Node(int val, int k) {
            this.prod = val % k;
            this.count[this.prod] = 1;
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.prod = (left.prod * right.prod) % k;
        for (int r = 0; r < k; r++) {
            res.count[r] += left.count[r];
            res.count[(left.prod * r) % k] += right.count[r];
        }
        return res;
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(nums[start], k);
            return;
        }
        int mid = (start + end) >> 1;
        build(nums, 2 * node, start, mid);
        build(nums, 2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = new Node(val, k);
            return;
        }
        int mid = (start + end) >> 1;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) >> 1;
        if (r <= mid) {
            return query(2 * node, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, end, l, r);
        }
        Node left = query(2 * node, start, mid, l, r);
        Node right = query(2 * node + 1, mid + 1, end, l, r);
        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];
        build(nums, 1, 0, n - 1);
        int qLen = queries.length;
        int[] result = new int[qLen];
        for (int i = 0; i < qLen; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            Node resNode = query(1, 0, n - 1, start, n - 1);
            result[i] = resNode.count[x];
        }
        return result;
    }
}