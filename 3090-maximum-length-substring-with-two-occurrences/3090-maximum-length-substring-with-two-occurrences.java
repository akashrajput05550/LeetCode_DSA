class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            count[rightChar - 'a']++;

            while (count[rightChar - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}