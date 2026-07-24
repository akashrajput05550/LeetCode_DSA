class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] hasPairXor = new boolean[2048];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                hasPairXor[nums[i] ^ nums[j]] = true;
            }
        }

        boolean[] hasTripletXor = new boolean[2048];
        for (int i = 0; i < 2048; i++) {
            if (hasPairXor[i]) {
                for (int num : nums) {
                    hasTripletXor[i ^ num] = true;
                }
            }
        }

        int count = 0;
        for (boolean present : hasTripletXor) {
            if (present) {
                count++;
            }
        }

        return count;
    }
}