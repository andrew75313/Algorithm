class Solution {
    // 방향키
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    static final int LIMIT = 1000000; // big num 변수화
    static final int MAX_CELLS = 20000; // 최대 셀 수 제한

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // blocked 좌표들을 set으로 변환
        Set<String> blockedSet = new HashSet<>();
        for (int[] coordinate : blocked) {
            blockedSet.add(coordinate[0] + "," + coordinate[1]);
        }

        // 1. source -> target으로의 경로가 가능한지 검사
        if (!bfs(source, target, blockedSet)) return false;

        // 2. target -> source으로의 경로가 가능한지 검사
        if (!bfs(target, source, blockedSet)) return false;

        return true;
    }

    // 경로 탐색 함수
    public boolean bfs(int[] source, int[] target, Set<String> blockedSet) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        
        visited.add(source[0] + "," + source[1]);
        queue.add(new int[]{source[0], source[1]});
        
        // 메모리 제한용 카운트
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currentX = current[0];
                int currentY = current[1];

                // 목표 좌표에 도달했으면 종료
                if (currentX == target[0] && currentY == target[1]) return true;

                // 상하좌우 탐색
                for (int d = 0; d < 4; d++) {
                    int nextX = currentX + dx[d];
                    int nextY = currentY + dy[d];

                    // 각 좌표가 0 이상 1000000 미만인지, 방문하지 않았고, blocked가 아닌지 확인
                    if (0 <= nextX && nextX < LIMIT && 0 <= nextY && nextY < LIMIT &&
                        !visited.contains(nextX + "," + nextY) && 
                        !blockedSet.contains(nextX + "," + nextY)) {
                        
                        // 방문처리
                        visited.add(nextX + "," + nextY);
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }

            // 탐색한 갯수를 추가하면서  count
            count += size;
            
            // 최대 탐색 셀을 넘으면 경로가 있다고 판단
            if (count > MAX_CELLS) return true; 
        }

        return false; // 목표에 도달하지 못한 경우
    }
}
