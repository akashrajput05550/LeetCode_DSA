class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];
        long total = 0;

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            long newSubseq = (total + 1 - last[idx] + MOD) % MOD;
            total = (total + newSubseq) % MOD;
            last[idx] = (last[idx] + newSubseq) % MOD;
        }

        return (int) total;
    }
}