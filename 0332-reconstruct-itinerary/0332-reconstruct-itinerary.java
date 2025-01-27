import java.util.*;

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> totalPath = new ArrayList<>();

        // 행지 마다 맵 리스트 생성
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(List<String> ticket : tickets) {
            String departure = ticket.get(0);
            String arrival = ticket.get(1);

            map.computeIfAbsent(departure, k -> new PriorityQueue<>()).add(arrival);
        }

        String departure = "JFK";
        dfs(totalPath, map, departure);


        return totalPath;

    }

    public void dfs(List<String> totalPath, Map<String, PriorityQueue<String>> map, String departure) {
        // departure에 해당하는 행선지
        PriorityQueue<String> arrivalQueue = map.get(departure);

        // 다음 행성지에서 출발하는 티켓이 없을 경우 까지 진행을 함
        while(arrivalQueue != null && !arrivalQueue.isEmpty()) {
            String arrival = arrivalQueue.poll();

            dfs(totalPath, map, arrival);
        }

        // 꼬리를 물어 모두 탐색했으므로, 맨 처음의 departure 을 주입
        totalPath.add(0, departure);
    }
}