class Solution {
    public int change(int amount, int[] coins) {
        
        // dp : value - every cases
        int[] dp = new int[amount + 1];

        // init
        dp[0] = 1;

        // fill
        for(int coin : coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}