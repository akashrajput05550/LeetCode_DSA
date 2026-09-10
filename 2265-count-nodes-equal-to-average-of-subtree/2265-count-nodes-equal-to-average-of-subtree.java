class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return matchingNodeCount;
    }

    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int sum = left[0] + right[0] + node.val;
        int count = left[1] + right[1] + 1;

        if (sum / count == node.val) {
            matchingNodeCount++;
        }

        return new int[]{sum, count};
    }
}