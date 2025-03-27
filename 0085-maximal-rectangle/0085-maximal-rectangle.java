class Solution {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Historgram
        // 높이 지정
        int[] height = new int[cols];

        // matrix row 별 높이 갱싱 -> 넓이 계산
        int maxArea = 0;
        for(char[] row : matrix) {
            // 연속된 1 누적
            for(int i = 0; i < cols; i++) {
                height[i] = row[i] == '1' ? height[i] + 1 : 0;
            }

            // 가장 큰 넓이  만들기
            maxArea = Math.max(maxArea, getMaxArea(height));
        }

        return maxArea;
    }

    public int getMaxArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for(int i = 0; i <= height.length; i++) {
            // 현재 높이
            int currentHeight = (i == height.length) ? 0 : height[i];

            // while 문으로 스택 다 쓸 때까지, 또는 peek 인덱스 height 값이 현재 height 보다 작을 때까지
            // maxArea 갱신
            while(!stack.isEmpty() && currentHeight < height[stack.peek()]) {
                
                // stack 비어있을 때 고려
                int index = stack.pop();

               int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                int h = height[index];

                maxArea = Math.max(maxArea, w * h);
            }

            // stack 푸시
            stack.push(i);
        }

        return maxArea;
    }
}