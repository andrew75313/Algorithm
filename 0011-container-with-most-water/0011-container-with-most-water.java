import java.util.*;

class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        
        // 기준점을 줄이면서 갯수를 계산
        int maxWater = 0;

        while(left < right) {
            int waterAmount = Math.min(height[left], height[right])*(right - left);
            maxWater = Math.max(waterAmount, maxWater);

            // 왼오 중 작은걸 안쪽으로 이동
            if(height[left] <= height[right]){
                left++;
            } else if(height[left] > height[right]) {
                right--;
            }
        }

        return maxWater;
    }
}