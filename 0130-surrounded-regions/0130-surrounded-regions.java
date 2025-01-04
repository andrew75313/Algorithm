import java.util.*;

class Solution {
    // 상하좌우 이동키 
    public int[] dx = new int[]{-1,1,0,0};
    public int[] dy = new int[]{0,0,-1,1};

    public char[][] solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        // 방문 확인, O 체크
        boolean[][] region = new boolean[n][m];

        // 테두리 방문 하면서 O 확인
        for(int i = 0; i < m; i++){
             if(board[0][i] == 'O') bfs(board, region, 0, i);
             if(board[n-1][i] == 'O') bfs(board, region, n-1, i);

        }

        for(int j = 0; j < n; j++) {
            if(board[j][0] == 'O') bfs(board, region, j, 0);
            if(board[j][m-1] == 'O') bfs(board, region, j,m-1);
        }

        // board를 모두 방문하면서, region이 아닌 것들 싹 X로 설정
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(region[i][j]) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }

        return board;
    }

    public void bfs(char[][] board, boolean[][] region, int x, int y) {

        // 큐만들기
        Queue<int[]> queue = new LinkedList<>();

        // 초기 설정
        queue.add(new int[]{x, y});
        region[x][y] = true;


        // while문
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currentX = current[0];
                int currentY = current[1];

            
                //region 등록
                region[currentX][currentY] = true;

                    // 상하좌우 확인
                    for(int j = 0; j < 4; j++) {
                       
                            int newX = currentX + dx[j];
                            int newY = currentY + dy[j];
                            // 다음 좌표가 O,  미방문이면 큐등록
                            if(newX >= 0 &&
                            newY >= 0 &&
                            newX < board.length &&
                            newY < board[0].length &&
                            !region[newX][newY] &&
                            board[newX][newY] == 'O') {
                                queue.add(new int[]{newX, newY});
                                region[newX][newY] = true;
                        }
                    }
            }
        }
    }
}