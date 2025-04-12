class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // DP setting
        int[][] dp = new int[m+1][n+1];

        // init setting
        dp[0][0] = 0;
        for(int i = 0; i <= m; i++) dp[i][0] = 0;
        for(int j = 0; j <= n; j++) dp[0][j] = 0;

        // dp (i, j are not index but ORDER)
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // prev 인덱스 값이 서로 같을 때 현재 +1갱신
                if(text1.charAt(i-1)== text2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1] + 1;
                }

                // 다를 때는 이전 dp 값중 가장 많은 경우의 수를 채택해서 등록
                if(text1.charAt(i-1) != text2.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }
}