class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        int targetIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                targetIndex = i;
                break;
            }
        }

        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(targetIndex + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }

        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(targetIndex - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }

        if (!isIncreasing && !isDecreasing) {
            return -1;
        }

        int minOps = Integer.MAX_VALUE;

        if (isIncreasing) {
            int leftRotations = targetIndex;
            minOps = Math.min(minOps, leftRotations);

            if (leftRotations > 0) {
                int rightRotations = n - leftRotations;
                minOps = Math.min(minOps, 2 + rightRotations);
            }
        }

        if (isDecreasing) {
            int leftRotations = (targetIndex + 1) % n;
            minOps = Math.min(minOps, 1 + leftRotations);

            int rightRotations = (n - leftRotations) % n;
            minOps = Math.min(minOps, 1 + rightRotations);
        }

        return minOps;
    }
}