class Solution {
    private int[] dx = {1, 0};
    private int[] dy = {0, 1};

    public int uniquePaths(int m, int n) {
        // Setting grid
        int[][] grid = new int[m][n];

        // DP setting
        // value :  possibilities to reach grid[i][j] 
        int[][] dp = new int[m][n];

        // init
        dp[0][0] = 1;
        for(int i = 1; i < n; i++) dp[0][i] = 1;
        for(int i= 1; i < m; i++) dp[i][0] = 1;

        // DP
        for(int x = 1; x < m; x++) {
            for(int y = 1; y < n; y++) {
                // validate coordinate i & j
                // sum all of the dp around current i,j
                for(int d = 0; d < 2; d++) {
                    int prevX = x - dx[d];
                    int prevY = y - dy[d];

                    if(0 <= prevX && prevX < m &&
                    0 <= prevY && prevY < n) {
                        dp[x][y] += dp[prevX][prevY];
                    }
                }
            }
        }

        return dp[m-1][n-1];
    }
}