class Solution {
    public int rob(int[] nums) {
        int numHouses = nums.length;

        if(numHouses == 1) return nums[0];

        //  dp 설정 , 초기화
        int[] dp = new int[numHouses];

        // 초기값 설정
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);

        // dp 진행
        for(int i = 2; i < numHouses; i++) {
            dp[i] = Math.max(dp[i-1], dp[i -2] + nums[i]);
        }

        // 마지막 dp 값 return
        return dp[numHouses - 1];
    }
}