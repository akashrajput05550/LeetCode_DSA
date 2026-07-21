import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        int totalOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<Integer> zeroBlocks = new ArrayList<>();
        List<Integer> oneBlocks = new ArrayList<>();

        int i = 0;
        int n = t.length();
        while (i < n) {
            int j = i;
            while (j < n && t.charAt(j) == t.charAt(i)) {
                j++;
            }
            int len = j - i;
            if (t.charAt(i) == '0') {
                zeroBlocks.add(len);
            } else {
                oneBlocks.add(len);
            }
            i = j;
        }

        int maxOnes = totalOnes;

        for (int k = 1; k < oneBlocks.size() - 1; k++) {
            int gainedOnes = zeroBlocks.get(k - 1) + zeroBlocks.get(k);
            maxOnes = Math.max(maxOnes, totalOnes + gainedOnes);
        }

        return maxOnes;
    }
}