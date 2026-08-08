class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] last = new int[m + 1];
        last[m] = n;
        
        for (int j = m - 1, i = n - 1; j >= 0; j--) {
            while (i >= 0 && word1.charAt(i) != word2.charAt(j)) {
                i--;
            }
            last[j] = i;
            if (i >= 0) {
                i--;
            }
        }
        
        int[] ans = new int[m];
        boolean changed = false;
        int i = 0;
        
        for (int j = 0; j < m; j++) {
            boolean found = false;
            while (i < n) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    if (!changed || last[j + 1] > i) {
                        ans[j] = i;
                        i++;
                        found = true;
                        break;
                    }
                } else {
                    if (!changed && last[j + 1] > i) {
                        ans[j] = i;
                        changed = true;
                        i++;
                        found = true;
                        break;
                    }
                }
                i++;
            }
            if (!found) {
                return new int[0];
            }
        }
        
        return ans;
    }
}