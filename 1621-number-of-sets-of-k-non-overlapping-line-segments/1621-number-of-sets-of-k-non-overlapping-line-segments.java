class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007L;
        int total = n + k - 1;
        int choose = 2 * k;

        if (choose > total) return 0;
        if (choose > total - choose) {
            choose = total - choose;
        }

        long num = 1;
        long den = 1;

        for (int i = 0; i < choose; i++) {
            num = (num * (total - i)) % MOD;
            den = (den * (i + 1)) % MOD;
        }

        return (int) (num * modInverse(den, MOD) % MOD);
    }

    private long modInverse(long base, long mod) {
        return power(base, mod - 2, mod);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}