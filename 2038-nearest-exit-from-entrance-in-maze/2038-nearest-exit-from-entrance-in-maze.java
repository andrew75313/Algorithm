class Solution {
    // direction key
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int startX = entrance[0];
        int startY = entrance[1];

        // 방문처리
        boolean[][] visited = new boolean[m][n];

        // 큐 만들기
        Queue<int[]> queue  = new LinkedList<>();

        // 초기 세팅
        visited[startX][startY] = true;
        queue.add(new int[]{startX, startY});

        // BFS 진행
        int count = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];

                // 끝에 다다르면 바로 반환
                if(count != 0 && (currX == 0 || currX == m-1 || currY == 0 || currY == n-1) ) return count;

                // 상하좌우로 움직임
                for(int d = 0; d < 4; d++) {
                    int nextX = currX + dx[d];
                    int nextY = currY + dy[d];

                    if(0<= nextX && nextX < m &&
                    0<= nextY && nextY < n &&
                    maze[nextX][nextY] == '.' &&
                    !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }
            count++;
        }
        
        return -1;
    }
}