import java.util.*;

class Solution {

    // 방향키
    int[] dx = new int[]{-1,1,0,0};
    int[] dy = new int[]{0,0,-1,1};

    public int containVirus(int[][] isInfected) {
        int n = isInfected.length;
        int m = isInfected[0].length;
        int totalWalls = 0;

        // isInfected에 바이러스가 모두 없어졌을때 종료 while 문 
        // 안에서 break; 할때 까지 반복
        while(true) {
            // 바이러스 감염 부분 확인하는 부분
            boolean[][] visited = new boolean[n][m];
            // 전체 바이러스 감염 가능성 영역
            List<Set<String>> infectableAreaList = new ArrayList<>();
            // 바이러스 영역 별 벽의 수
            List<Integer> wallCountList = new ArrayList<>();
            // 바이러스 영역의 모음
            List<Set<String>> virusAreaList = new ArrayList<>();
            

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    Set<String> virusArea = new HashSet<>();
                    Set<String> infectableArea = new HashSet<>();
                    int[] wallCount = new int[1];

                    // 바이러스 일경우, dfs 진행하면서 필요한 wall 진행
                    // dfs를 통해 주변 바이러스좌표 방문처리
                    // 상하좌우 돌면서 0이면 wall 총 갯수 파악, 등록
                    if(isInfected[i][j] == 1 && !visited[i][j]) {
                        dfs(isInfected, visited, i, j, virusArea, infectableArea, wallCount);
                        
                        // 전체 리스트에 해당 내용 추가
                        virusAreaList.add(virusArea);
                        infectableAreaList.add(infectableArea);
                        wallCountList.add(wallCount[0]);
                    }
                }
            }

            // 가장 많은 infectableArea을 가진 바이러스영역을 찾기
            // 해당 영역의 순번찾기
            int worstAreaNum = 0;
            int size = 0;
            for(int i = 0; i < infectableAreaList.size(); i++) {
                if(infectableAreaList.get(i).size() > infectableAreaList.get(worstAreaNum).size()) {
                    worstAreaNum = i;
                }
            }

            if(wallCountList.isEmpty()) return 0;

            // 총 벽 수에 더하기
            totalWalls += wallCountList.get(worstAreaNum);

            // 해당 영역 전부 -1로 바꾸기
            for(String coordinate : virusAreaList.get(worstAreaNum)) {
                String[] coordinateArr = coordinate.split(",");

                int coordinateX = Integer.parseInt(coordinateArr[0]);
                int coordinateY = Integer.parseInt(coordinateArr[1]);
                isInfected[coordinateX][coordinateY] = -1;
            }

            // 확산영역 전부 1로 바꾸기
            for(int i = 0; i < infectableAreaList.size(); i++) {
                if(i != worstAreaNum) {
                    for(String coordinate : infectableAreaList.get(i)) {
                        String[] coordinateArr = coordinate.split(",");

                        int coordinateX = Integer.parseInt(coordinateArr[0]);
                        int coordinateY = Integer.parseInt(coordinateArr[1]);

                        isInfected[coordinateX][coordinateY]= 1;
                    }
                }
            }

            // 바이러스가 하나도 없으면 1이상이 없으면 while문 종료
            boolean isCured = true;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(isInfected[i][j] == 1) isCured = false;
                }
            }

            if(isCured) break;
        }
        
        return totalWalls;
    }

    public void dfs(int[][] isInfected, boolean[][] visited, int x, int y, Set<String> virusArea, Set<String> infectableArea, int[] wallCount) {
        int n = isInfected.length;
        int m = isInfected[0].length;
        
        // 방문처리, area 등록
        visited[x][y] = true;
        virusArea.add(x+","+y);

        // 상하좌우 방문
        for(int d = 0; d < 4; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            // 1일경우+미방문시 해당 좌표를 dfs 넣어서 다시 추가, 0일 경우 wall카운트와 해당 좌표는 미래의 감염가능성 영역
            if(0 <= nextX && nextX < n &&
            0 <= nextY && nextY < m) {
                if(isInfected[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    dfs(isInfected, visited, nextX, nextY, virusArea, infectableArea, wallCount);
                } else if(isInfected[nextX][nextY] == 0) {
                    infectableArea.add(nextX+","+nextY);
                    wallCount[0]++;
                }
            }
        }
    }
}