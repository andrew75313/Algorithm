 import java.util.*; 

class Solution {

    public int[] dp;
    public int[] count;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {

        dp = new int[n];
        count = new int[n];

        // graph 만들기
        Map<Integer, List<Integer>> graph =  new HashMap<>();
        for(int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            graph.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
            graph.computeIfAbsent(end, k -> new ArrayList<>()).add(start);
        }

        // dfs 재귀를 통한 0을 최종 부모로 한 dp 저장
        // 초기 설정
        int node = 0;
        int parent = -1;
        dfs(graph, node, parent);

        // dp 저장값을 토대로 각 노드별 누적값 계산
        // 초기설정
        int[] total = new int[n];
        total[node] = dp[node];
        calculateDist(graph, node, parent, total);

        return total;
        
    }

    public void dfs(Map<Integer, List<Integer>> graph, int node, int parent) {
        dp[node] = 0;
        count[node] = 1;

        if(graph.get(node) == null) return;

        for(int neighbor : graph.get(node)) {
            // 역주행 막기
            if(neighbor == parent) continue;

            // 재귀
            dfs(graph, neighbor, node);

            // 노드 갯수 누적
            count[node] += count[neighbor];

            //dp 누적
            dp[node] += dp[neighbor] + count[neighbor];
        }
    }

    public void calculateDist(Map<Integer, List<Integer>> graph, int node, int parent, int[] total) {
        if(graph.get(node) == null) return;

        for(int neighbor :  graph.get(node)) {
            // 역주행 막기
            if(neighbor == parent) continue;

            // parent(node) 를 활용해서 neighbor 까지의 누적 계산
            total[neighbor] = total[node] - count[neighbor] + (dp.length - count[neighbor]);

            // 재귀
            calculateDist(graph, neighbor, node, total);
        }
    }
}