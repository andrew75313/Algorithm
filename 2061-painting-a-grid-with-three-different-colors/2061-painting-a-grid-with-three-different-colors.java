class Solution {

    private final int MOD = 1000000007;

    public int colorTheGrid(int m, int n) {

        // 한 열에 만들 수 있는 모든 패턴 구하기
        List<Integer> patterns = new ArrayList<>();
        findPatterns(patterns, m, 0, 0 );

        // DP 테이블 생성
        int patternSize = patterns.size();
        int[][] dp = new int[n][patternSize];

        // 초기값 설정
        // 모든 패턴은 첫번째에 하나밖에 누적이 안되어있으므로
        Arrays.fill(dp[0], 1);

        // 1~n 열 까지 진행하면서 패턴끼리 조합 DP 저장
        for(int col = 1; col < n; col++) {
            for(int i = 0; i < patternSize; i++) {
                for(int j = 0; j < patternSize; j++) {
                    // i 번 패턴과 j번 패턴과 비교 : 좌우 인접하는지 비교
                    int patternA = patterns.get(i);
                    int patternB = patterns.get(j);

                    boolean isValid = true;
                    for(int p = 0; p < m; p++) {
                        if((patternA & 3) == (patternB & 3)) {
                            isValid = false;
                            break;
                        } else {
                            patternA >>= 2;
                            patternB >>= 2;
                        }
                    }

                    if(isValid) {
                        dp[col][i] = ( dp[col][i] + dp[col-1][j] ) % MOD;
                    }


                }
            }
        }

        // 마지막 DP 모든 값 다 더하기
        int answer = 0;
        for(int count : dp[n-1]) {
            answer = ( answer + count ) % MOD; 
        }
        
        return answer;
    }

    // 재귀하면서 3가지 색으로 만들수 있는 모든 조합 저장
    public void findPatterns(List<Integer> patterns, int m, int count, int pattern) {
        // m 개가 만들어지면 patterns 에 넣기
        if(m == count) {
            patterns.add(pattern);
            return;
        }

        // RGB 3개 각각 01 10 11 로 비트마스크로 진행 
        for(int color = 1; color <= 3; color++ ) {
            if( count == 0 || (pattern & 3) != color ) {
                findPatterns(patterns, m, count +1, (pattern << 2) | color);
            }
        }
    }


}