import java.util.*;

class Solution {
    public int maxSubArray(int[] nums) {
        // 테이블 설정
        int[] dp = new int[nums.length];

        // 초기 설정
        dp[0] = nums[0];
        int max = nums[0];

        // 반복하면서 DP
        for(int i = 1; i < nums.length; i++) {
            // 이전까지 합 VS 현재 값
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            // 최댓값 설정
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
}