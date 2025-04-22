class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp : value -> usages of m & n
        int[][] dp = new int[m+1][n+1];

        // init -> no need

        // dp update (backwards)
        for(String str : strs) {

            // count 1 & 0
            int count0 = 0;
            int count1 = 0;
            for(char c : str.toCharArray()){
                if(c == '0') count0++;
                if(c == '1') count1++;
            }

            // update
            for(int i = m; i >= count0; i--) {
                for(int j = n; j >= count1 ; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count0][j - count1] + 1);
                }
            }
        }

        return dp[m][n];

    }
}