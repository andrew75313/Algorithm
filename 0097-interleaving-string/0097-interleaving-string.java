import java.util.*;

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();

        // 갯수가 안맞을 때의 예외
        if(n+m != l) return false;
        
        // DP 테이블 생성 : s1 s2 각각 인덱스 값을 조합해서 s3 만들어지는지 확인
        boolean[][] dp =  new boolean[n+1][m+1];

        // 초기화
        dp[0][0] = true;

        // DP 채우기
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <=m; j++) {
                // s1에서 쓰기
                if (i > 0 &&
                s1.charAt(i - 1) == s3.charAt(i + j - 1) &&
                dp[i-1][j]) {
                    dp[i][j] = true;
                }

                // s2에서 쓰기
                if(j > 0 && 
                s2.charAt(j - 1) == s3.charAt(i + j - 1) &&
                dp[i][j-1]) {
                    dp[i][j] = true;
                }
            }
        }
      
        return dp[n][m];
    }
}