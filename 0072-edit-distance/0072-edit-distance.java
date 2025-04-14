class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // dp : value -> min op nums
        int[][] dp = new int[n+1][m+1];

        // setting init (consider word1 or word2 is blank)
        for(int i = 0; i <= n; i++) dp[i][0] = i;
        for(int i = 0; i <= m; i++) dp[0][i] = i;

        // dp
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);

                // 일치 할 경우
                if(c1 == c2) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
            
                // 일치하지 않을 경우
                // 교체
                int rep = dp[i-1][j-1]+1;

                // 삽입 to c2
                int ins = dp[i][j-1] +1;

                // 삭제 to c1
                int del = dp[i-1][j] +1;

                dp[i][j] = Math.min(rep, Math.min(ins,del));
            }
        }
        
        return dp[n][m];
    }
}