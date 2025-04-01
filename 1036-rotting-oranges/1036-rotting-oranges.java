class Solution {
    // 방향키
    public int[] dx = {-1, 1, 0, 0};
    public int[] dy = {0 , 0, -1, 1};

    public int orangesRotting(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        //  썩은 갯수 좌표 리스트, 안썩은 갯수 총 세기
        Queue<int[]> rottenQueue = new LinkedList<>();
        int numFresh = 0;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(grid[i][j] == 1) numFresh++;

                if(grid[i][j] == 2) {
                    rottenQueue.add(new int[]{i, j});
                }
            }
        }

        // 예외처리
        if(numFresh == 0) return 0;

        // 반환될 totalCount를 추가
        return bfs(grid, rottenQueue, numFresh);

    }

    public int bfs(int[][] grid, Queue<int[]> rottenQueue, int numFresh) {
        int m = grid.length;
        int n = grid[0].length;
        // 반환할 시간 
        int min = 0;

        // while문으로 queue 순회
        while(!rottenQueue.isEmpty()) {
            int size = rottenQueue.size();

            for(int i = 0; i < size; i++){
                int[] current = rottenQueue.poll();
                int currX = current[0];
                int currY = current[1];

                // 상하좌우 한번씩 반복해서 진행
                for (int d = 0; d < 4; d++) {
                    int nextX = currX + dx[d];
                    int nextY = currY + dy[d];

                    // 좌표 영역내에서
                    // 신선할 경우에만, 2로 썩게하기, 큐넣기, 신선한오렌지 감소
                    if(0 <= nextX && nextX < m &&
                    0 <= nextY && nextY <n &&
                        grid[nextX][nextY] == 1) {
                        grid[nextX][nextY] = 2;
                        rottenQueue.add(new int[]{nextX, nextY});
                        numFresh--;
                    }
                }
            }
            
            if(!rottenQueue.isEmpty()) min++;
        }

        // 전체감염을 못시킬 경우, -1
        if(numFresh != 0) min = -1;

        return min;
    }
}