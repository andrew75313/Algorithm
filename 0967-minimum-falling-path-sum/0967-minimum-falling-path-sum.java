class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        // dp
        int[][] dp = new int[n][n];
        
        // dp init
        for(int c = 0; c < n; c++) {
            dp[n-1][c] = matrix[n-1][c];
        }

        // update
        for(int r = n-2; r >= 0; r--) {
            for(int c = 0; c < n; c++) {
                int left = c - 1 < 0 ? Integer.MAX_VALUE : dp[r + 1][c - 1];
                int middle = dp[r + 1][c];
                int right = c + 1 >= n ? Integer.MAX_VALUE : dp[r+1][c + 1]; 

                dp[r][c] = matrix[r][c] + Math.min(left, Math.min(middle, right));
            }
        }
        
        // find minimum in the first row
        int result = Integer.MAX_VALUE;
        for(int val : dp[0]) {
            result = Math.min(result, val);
        }
        
        return result;
    }
}