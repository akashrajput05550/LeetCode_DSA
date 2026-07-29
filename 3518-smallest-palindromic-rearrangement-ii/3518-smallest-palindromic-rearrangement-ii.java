class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int[] half = new int[26];
        int totalHalfLen = 0;
        char midChar = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            totalHalfLen += half[i];
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
        }

        if (countPermutations(half, totalHalfLen) < k) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();
        for (int pos = 0; pos < totalHalfLen; pos++) {
            for (int ch = 0; ch < 26; ch++) {
                if (half[ch] == 0) {
                    continue;
                }

                half[ch]--;
                long ways = countPermutations(half, totalHalfLen - pos - 1);

                if (ways >= k) {
                    leftHalf.append((char) ('a' + ch));
                    break;
                } else {
                    k -= ways;
                    half[ch]++;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(leftHalf);
        if (midChar != 0) {
            result.append(midChar);
        }
        result.append(new StringBuilder(leftHalf).reverse());

        return result.toString();
    }

    private long countPermutations(int[] counts, int total) {
        long res = 1;
        int remaining = total;

        for (int i = 0; i < 26; i++) {
            int cnt = counts[i];
            for (int j = 1; j <= cnt; j++) {
                res = res * (remaining - cnt + j) / j;
                if (res > LIMIT) {
                    return LIMIT;
                }
            }
            remaining -= cnt;
        }

        return Math.min(res, LIMIT);
    }
}