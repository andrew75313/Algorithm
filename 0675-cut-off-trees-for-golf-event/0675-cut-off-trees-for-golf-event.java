import java.util.*;

class Solution {
    // 방향키
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    public int cutOffTree(List<List<Integer>> forest) {
        int n = forest.size();
        int m = forest.get(0).size();

        // 나무 리스트 만들기 및 검증
        List<int[]> treeList = new ArrayList<>();
       
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Integer treeheight = forest.get(i).get(j);
                if(treeheight != 0 && treeheight != 1) {
                    treeList.add(new int[]{i, j, treeheight});
                };
            }
        }

        // 높이 순으로 정리
        Collections.sort(treeList, (a, b) -> a[2] - b[2]);

        // 최단거리 합
        int answer = 0;
        int startX = 0;
        int startY = 0;
        
        for(int[]tree : treeList) {
            int endX = tree[0];
            int endY = tree[1];

            int path = bfs(forest, startX, startY, endX, endY);
            if(path == -1) {
                return -1;
            } else {
                answer += path;
            }

            startX = endX;
            startY = endY;
        }

        return answer;
    }

    public int bfs(List<List<Integer>> forest, int startX, int startY, int endX, int endY) {
        int n = forest.size();
        int m = forest.get(0).size();

        // visited 정리
        boolean[][] visited = new boolean[n][m];

        // queue 만들기
        Queue<int[]> queue =  new LinkedList<>();

        // 초기 세팅 및 0,0 나무 잘렸는지 여부
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        // while문
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currentX = current[0];
                int currentY = current[1];

                // end 좌표 도달시
                if(currentX ==  endX && currentY == endY) return step;


                // 상하좌우 이동
                List<Integer> temp = new LinkedList<>();
                for(int d = 0; d < 4; d++) {
                    int newX = currentX + dx[d];
                    int newY = currentY + dy[d];

                    if(0 <= newX && newX < n &&
                    0 <= newY && newY < m &&
                    !visited[newX][newY] &&
                    forest.get(newX).get(newY) != 0) {
                        
                        visited[newX][newY] = true;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }   

            step++;
        }

        // 실패시
        return -1;
    }
}