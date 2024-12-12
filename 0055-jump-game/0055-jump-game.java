import java.util.*;

class Solution {
    public boolean canJump(int[] nums) {
        // DP 굳이 사용 X
        // 하나씩 진행하면서 순서대로 진행 -> 마지막 순서에 정상적으로 도달하는지만 확인

        int reach = 0;

        for(int i = 0; i < nums.length; i++) {
            // 인덱스 값이 0일 경우 나아가지 false
            if(i > reach) return false;

            // 도달 최대거리 갱신
            reach = Math.max(reach, i + nums[i]);

            // 마지막 인덱스 넘어가는지 확인
            if(reach >= nums.length-1) return true;

        }

        return false;   
    }
}