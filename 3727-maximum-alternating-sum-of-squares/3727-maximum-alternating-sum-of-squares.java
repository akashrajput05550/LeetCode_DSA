import java.util.Arrays;
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(squares);
        int posCount = (n + 1) / 2;
        int negCount = n / 2;
        long sum = 0;
        for (int i = 0; i < negCount; i++) {
            sum -= squares[i];
        }
        for (int i = n - posCount; i < n; i++) {
            sum += squares[i];
        }
        return sum;
    }
}