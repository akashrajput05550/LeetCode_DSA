import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        Arrays.sort(freq);
        
        int totalPushes = 0;
        for (int i = 0; i < 26; i++) {
            int currentFreq = freq[25 - i];
            if (currentFreq == 0) {
                break;
            }
            int pushesPerChar = (i / 8) + 1;
            totalPushes += currentFreq * pushesPerChar;
        }
        
        return totalPushes;
    }
}