class Solution {
    public int minMoves(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        }

        int maximumVal =nums[0];
        for(int i=1; i<nums.length; i++){
            if(nums[i]> maximumVal ){
                maximumVal=nums[i];
            }
        }
        int moves=0;
        for(int i=0; i<nums.length; i++){
            moves += (maximumVal-nums[i]);
        }
        return moves;
    }
}