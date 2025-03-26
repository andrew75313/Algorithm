class Solution {
    public int trap(int[] height) {
        int n = height.length;

        // 왼쪽 최댓값 담기
        int[] left =  new int[n];
        int leftMax = height[0];
        for(int i = 0; i <n; i++) {
            leftMax = Math.max(leftMax, height[i]);
            left[i] = leftMax;
        }
        // 오른쪽 최댓값 담기
        int[] right = new int[n];
        int rightMax = height[n-1];
        for(int i =  (n-1); i >=0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            right[i] = rightMax;
        }


        // 물을 담기
        int water = 0;
        for(int i  = 0; i < n; i++) {
            // 둘중에 작은 만큼 물을 담을 수 있고 그 사이 올라온 땅만큼 빼기
            water += Math.min(left[i], right[i]) - height[i];
        }

        return water;
    }
}
