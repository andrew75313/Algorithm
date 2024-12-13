import java.util.*;

class Solution {
    public int numDecodings(String s) {
        // dp 설정
        int n = s.length();
        int[] dp = new int[n+1];

        // 초기 설정
        if(s.charAt(0) == '0') return 0;
        dp[0] = 1;
        dp[1] = 1;

        // 테이블 채우기
        for(int i = 2; i < n+1; i++) {
            int one = Integer.parseInt(s.charAt(i-1) + "");
            int two = Integer.parseInt(s.substring(i-2, i));
            boolean oneCheck = true;
            boolean twoCheck = true;

            // 한개의 숫자일때
            if(0 < one && one <= 9) {
                dp[i] += dp[i-1];
            } else {
                oneCheck = false;
            }

            // 두개 숫자일때
            if(10 <= two && two <= 26) {
                dp[i] += dp[i-2];
            } else {
                twoCheck = false;
            }

            // 둘다 아닐때 false
            if(oneCheck == false && twoCheck == false) {
                return 0;
            }
        }

        return dp[n];
    }
}