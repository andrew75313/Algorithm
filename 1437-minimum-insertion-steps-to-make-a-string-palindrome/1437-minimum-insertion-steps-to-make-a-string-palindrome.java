class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        
        String reverse = new StringBuilder(s).reverse().toString();

        // dp
        // value -> common count
        int[][] dp = new int[n+1][n+1];

        // update
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                // if same with reverse -> update
                // if they aren't -> add sth would happen, and update Max
                if(s.charAt(i-1) == reverse.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return n - dp[n][n];
    }
}