import java.util.*;

class Solution {
    public int numSquares(int n) {
        // dp val -> squares counts
        int[] dp = new int[n+1];

        // init 
        dp[0] = 0;

        // update dp
        for(int i = 1; i <= n; i++) {
             dp[i] = Integer.MAX_VALUE;
            for(int j = 1; j*j <= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }

        return dp[n];
    }
}