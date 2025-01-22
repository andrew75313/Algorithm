import java.util.*;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 비밀멤버
        Set<Integer> secretMembers = new HashSet<>();

        // 초기 세팅
        secretMembers.add(0); // 0번 사람은 항상 비밀을 알고 있음
        secretMembers.add(firstPerson);

        // 미팅 정보를 시간별 map
        Map<Integer, List<int[]>> meetingMap = new TreeMap<>();
        for (int[] meeting : meetings) {
            meetingMap.computeIfAbsent(meeting[2], key -> new ArrayList<>()).add(meeting);
        }

        // 각 시간에 대해 DFS 수행
        for (int time : meetingMap.keySet()) {
            // 현재 시간의 미팅
            List<int[]> currentMeetingList = meetingMap.get(time);

            // 같은 시간대,에 만나는 사람당 사람들 저장
            Map<Integer, List<Integer>> currentMap = new HashMap<>();
            Set<Integer> visited = new HashSet<>();

            for (int[] meeting : currentMeetingList) {
                int personA = meeting[0];
                int personB = meeting[1];

                currentMap.computeIfAbsent(personA, k -> new ArrayList<>()).add(personB);
                currentMap.computeIfAbsent(personB, k -> new ArrayList<>()).add(personA);
            }


            for (int[] meeting : currentMeetingList) {
                if (!visited.contains(meeting[0]) &&
                secretMembers.contains(meeting[0])) {
                    dfs(currentMap, secretMembers, meeting[0], visited);
                }
                if (!visited.contains(meeting[1]) &&
                secretMembers.contains(meeting[1])) {
                    dfs(currentMap, secretMembers, meeting[1], visited);
                }
            }
        }

        // 결과를 정렬된 리스트로 반환
        List<Integer> secretList = new ArrayList<>(secretMembers);
        Collections.sort(secretList);
        
        return secretList;
    }

    // DFS 재귀 메서드
    private void dfs(Map<Integer, List<Integer>> currentMap, Set<Integer> secretMembers, int person, Set<Integer> visited) {
        // 사람 이미 방문했으면 넘기기
        if (visited.contains(person)) return;

        // 초기 설정
        visited.add(person);
        secretMembers.add(person);

        // 현재 노드와 연결된 노드 탐색
        if (currentMap.containsKey(person)) {
            for (int neighbor : currentMap.get(person)) {
                dfs(currentMap, secretMembers, neighbor, visited);
            }
        }
    }
}
