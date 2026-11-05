import java.util.Arrays;
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int val : arr1) {
            int left = 0;
            int right = arr2.length - 1;
            boolean isValid = true;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (Math.abs(arr2[mid] - val) <= d) {
                    isValid = false;
                    break;
                }

                if (arr2[mid] < val) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (isValid) {
                count++;
            }
        }

        return count;
    }
}