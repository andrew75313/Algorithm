import java.util.*;

class Solution {

    // 방향키
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public int largestIsland(int[][] grid) {
        int n = grid.length;

        // 섬별(아이디 생성) 크기 저장 map 생성
        Map<Integer, Integer> islands = new HashMap<>();

        int islandId = 10;

        // 그리드 하나씩 점검하면서
        // 1일경우 DFS로 섬 등록
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                
                int[] size = new int[]{0};

                dfs(grid, i , j, islandId, size);

                islands.put(islandId, size[0]);

                islandId++;
                }
            }
        }


        // grid 순회하면서 0일경우
        // 상하좌우 움직이면서 islandId 수집
        // 수집한 island 합을 계속 max와 비교
        int max = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    // 수집할 Set
                    Set<Integer> adjacentIslands = new HashSet<>();
                    int totalSize = 0;

                    // 상하좌우 움직이기
                    for(int d = 0; d < 4; d++) {
                        int nextX = i + dx[d];
                        int nextY = j + dy[d];

                        if(0 <= nextX && nextX < n &&
                        0 <= nextY && nextY < n &&
                        grid[nextX][nextY] >= 10) {
                            adjacentIslands.add(grid[nextX][nextY]);
                        }
                    }

                    // 수집한 아이디별 합으로 총합갱신
                    for (int id : adjacentIslands) {
                        totalSize += islands.get(id);
                    }

                    max = Math.max(max, totalSize + 1);
                }

            }
        }

        // max가 -1 :  0 자체가 없는경우, 전체가 1임을 가정
        return max == -1 ? n * n : max;
    }


    public void dfs(int[][] grid, int startX, int startY, int islandId, int[] size) {
        int n = grid.length;

        // 초기 설정
        grid[startX][startY] = islandId;
        size[0]++;
        
        // 상하좌우로 움직이면서
        // 그리드 안에서
        // 1일경우에만
        // 재귀 진행
        for(int d = 0; d < 4; d++) {
            int nextX = startX + dx[d];
            int nextY = startY + dy[d];

            if(0 <= nextX && nextX < n &&
            0 <= nextY && nextY < n &&
            grid[nextX][nextY] == 1) {
                dfs(grid, nextX, nextY, islandId, size);
            }
        }

    }
}