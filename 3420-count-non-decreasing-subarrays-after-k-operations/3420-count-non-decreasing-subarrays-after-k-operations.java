import java.util.ArrayDeque;
import java.util.Deque;
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long ops = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            int count = 1;
            while (!deque.isEmpty() && deque.peekFirst()[0] < nums[l]) {
                int[] top = deque.pollFirst();
                ops += (long) (nums[l] - top[0]) * top[1];
                count += top[1];
            }
            deque.offerFirst(new int[]{nums[l], count});

            while (ops > k) {
                int[] back = deque.peekLast();
                ops -= (long) (back[0] - nums[r]);
                back[1]--;
                if (back[1] == 0) {
                    deque.pollLast();
                }
                r--;
            }

            ans += (r - l + 1);
        }

        return ans;
    }
}