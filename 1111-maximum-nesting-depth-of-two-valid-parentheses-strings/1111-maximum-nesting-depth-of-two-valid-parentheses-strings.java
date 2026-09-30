class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n =seq.length();
        int[] ans =new int[n];
        int depths =0;
        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depths++;
                ans[i] = depths % 2;
            } else {
                ans[i] = depths % 2;
                depths--;
            }
        }
        return ans;
    }
}