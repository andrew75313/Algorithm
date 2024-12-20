import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int totalRoom = rooms.size();

        boolean[] visited = new boolean[totalRoom];

        return bfs(rooms, visited);
    }

    public boolean bfs(List<List<Integer>> rooms, boolean[] visited) {
        // 큐 만들기
        Queue<Integer> queue = new LinkedList<>();

        // 초기 설정
        List<Integer> firstRoom =  rooms.get(0);
        queue.add(0);
        visited[0] = true;

        // while문
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int current = queue.poll();

                // 방에서 키 꺼내기
                List<Integer> keyList = rooms.get(current);
                for(Integer key : keyList) {
                    if(!visited[key]) {
                        queue.add(key);
                        visited[key] = true;
                    }
                }

            }
        }

        // 한 방이라도 false라면 false 반환 그외 모두 true
        for(boolean isVisited : visited) {
            if(!isVisited) return false;
        }

        return true;
    }
}