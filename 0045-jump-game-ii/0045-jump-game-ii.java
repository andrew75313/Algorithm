class Solution {
    public int jump(int[] nums) {
        
        int n = nums.length;
        int jumps = 0; // 최소 점프 횟수
        int farthest = 0; // 현재 점프에서 도달할 수 있는 가장 먼 인덱스
        int currentEnd = 0; // 현재 점프에서의 끝 인덱스

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]); // 가장 멀리 갈 수 있는 범위를 업데이트

            if (i == currentEnd) { // 현재 점프가 끝나는 지점에 도달하면
                jumps++; // 점프 횟수 증가
                currentEnd = farthest; // 다음 점프의 끝을 갱신
            }
        }

        return jumps; // 최소 점프 횟수 반환
    }
}