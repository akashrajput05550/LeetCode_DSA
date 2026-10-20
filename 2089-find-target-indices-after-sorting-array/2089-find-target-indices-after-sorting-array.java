class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int num =0;
        int tCount=0;

        for(int ele : nums){
            if(ele == target){
                tCount++;
            }
            else if(ele<target){
                num++;
            }
        }
        List<Integer> ans =new ArrayList<>();
        while(tCount>0){
            ans.add(num);
            num++;
            tCount--;
        }
        return ans;
    }
}