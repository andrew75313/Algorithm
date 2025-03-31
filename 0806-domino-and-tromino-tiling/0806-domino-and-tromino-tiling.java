class Solution {

    private final int MOD = 1000000007;

    public int numTilings(int n) {
        
        if( n == 1) return 1;
        if( n == 2 ) return 2;
        
        // dp 설정 n번째 -> 인덱스 무시 0 무효
        long[] dp = new long[n+1];

        // 초기 설정
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5; 

        // dp 
        for(int i = 4; i <= n; i++) {
            // 1개도미노 세로  + 2개 도미노 가로  + 2개 트리미노 2경우
            dp[i] = ( dp[i-1] *2+ dp[i-3] ) % MOD;
        }
        
        // 마지막 값 반환
        return (int)dp[n];
    }
}