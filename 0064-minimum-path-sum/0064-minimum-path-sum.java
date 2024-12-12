import java.util.*;

class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // dp 테이블
        int[][] dp = new int[n][m];

        // 초기세팅 처음 지점, 맨 왼쪽 열, 첫 행 
        dp[0][0] = grid[0][0];
        for(int i = 1; i < n; i++){
            dp[i][0] = grid[i][0] + dp[i-1][0]; 
        }
        for(int j = 1; j < m; j++) {
            dp[0][j] = grid[0][j] + dp[0][j-1];
        }

        // 반복하면서 왼쪽에서 올때 위에서 올때 경우 정리
        // 두 방향에서 오는 값중 최솟값 + 그리드 값을 테이블에 저장
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        // bottom right 반환
        return dp[n - 1][m -1];
    }
}