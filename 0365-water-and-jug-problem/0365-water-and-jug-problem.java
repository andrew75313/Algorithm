import java.util.*;

class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        // Edge case: 목표가 두 물통 용량 합보다 크면 불가능
        if (target > x + y) return false;

        // BFS 방문 상태를 저장할 배열
        boolean[][] visited = new boolean[x + 1][y + 1];

        // BFS 수행
        return bfs(x, y, visited, target);
    }

    private boolean bfs(int x, int y, boolean[][] visited, int target) {
        // 큐 생성
        Queue<int[]> queue = new LinkedList<>();

        // 초기 상태 추가
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        // BFS 탐색
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int firstBucket = node[0];
            int secondBucket = node[1];

            // 목표를 달성했는지 확인
            if (firstBucket == target || secondBucket == target || firstBucket + secondBucket == target) {
                return true;
            }

            // 가능한 상태 전이
            int[][] tempArray = {
                {firstBucket, 0}, // 첫 번째 물통 비우기
                {0, secondBucket}, // 두 번째 물통 비우기
                {x, secondBucket}, // 첫 번째 물통 가득 채우기
                {firstBucket, y}, // 두 번째 물통 가득 채우기
                {firstBucket - Math.min(firstBucket, y - secondBucket), secondBucket + Math.min(firstBucket, y - secondBucket)}, // 첫 번째 -> 두 번째로 물 붓기
                {firstBucket + Math.min(secondBucket, x - firstBucket), secondBucket - Math.min(secondBucket, x - firstBucket)} // 두 번째 -> 첫 번째로 물 붓기
            };

            for (int[] temp : tempArray) {
                int a = temp[0];
                int b = temp[1];

                // 범위를 벗어나지 않고 방문하지 않은 상태만 큐에 추가
                if (a >= 0 && b >= 0 && !visited[a][b]) {
                    queue.add(new int[]{a, b});
                    visited[a][b] = true;
                }
            }
        }

        // 목표를 달성하지 못한 경우
        return false;
    }
}
