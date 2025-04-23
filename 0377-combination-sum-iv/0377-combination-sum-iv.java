class Solution {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;

        // dp val -> total count
        int[] dp  = new int[target + 1];

        // init
        dp[0] = 1;

        // update dp
        for(int i = 1; i <= target; i++) {
            for(int num : nums) {
                if(i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        
        return dp[target];
    }
}