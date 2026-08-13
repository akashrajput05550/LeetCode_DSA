class Solution {
    private class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char prefChar;
        char suffChar;
        int totalLen;

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.prefChar = c;
            this.suffChar = c;
            this.totalLen = 1;
        }

        Node() {
            this.maxLen = 0;
            this.prefLen = 0;
            this.suffLen = 0;
            this.prefChar = ' ';
            this.suffChar = ' ';
            this.totalLen = 0;
        }
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        if (left.totalLen == 0) return right;
        if (right.totalLen == 0) return left;

        Node res = new Node();
        res.totalLen = left.totalLen + right.totalLen;

        res.prefChar = left.prefChar;
        res.prefLen = left.prefLen;
        if (left.prefLen == left.totalLen && left.prefChar == right.prefChar) {
            res.prefLen += right.prefLen;
        }

        res.suffChar = right.suffChar;
        res.suffLen = right.suffLen;
        if (right.suffLen == right.totalLen && right.suffChar == left.suffChar) {
            res.suffLen += left.suffLen;
        }

        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffChar == right.prefChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            chars[idx] = ch;
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (start <= idx && idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = tree[1].maxLen;
        }

        return result;
    }
}