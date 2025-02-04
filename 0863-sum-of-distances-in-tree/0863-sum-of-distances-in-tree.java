class Solution {

    public int[] distance;
    public int[] count;
    public List<List<Integer>> graph;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] total = new int[n];

        // graph 만들기
        graph = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // graphy 기준 0 ~ 타겟노드 까지의 거리 distance에 넣기
        distance = new int[n];
        count = new int[n];

        // 0 부터 타겟까지의 거리를 미리 저장
        int target  = 0;
        int parent = -1;
        dfs(target, parent);

        // 노드간의 거리를 계산해서 total에 저장
        total[target] = distance[target];
        calculateDist(target, parent, total);

        return total;
        
    }

    public void dfs(int target, int parent) {
        // 초기설정 본인 노드 세기, 거리 세기
        count[target] = 1;
        distance[target] = 0;

        for(int neighbor : graph.get(target)) {
            // 부모가 똑같은 노드는 역행 막기 위해 패스
            if(neighbor == parent) continue;

            // 재귀
            dfs(neighbor, target);

            count[target] += count[neighbor];

            // 결국 타겟까지의 거리는 dfs를 통한 노드의 갯수
            distance[target] += distance[neighbor] + count[neighbor];
        }
    }

    public void calculateDist(int target, int parent, int[] total) {
        for(int neighbor : graph.get(target)) {
            // 부모가 똑같은 노드는 역행 막기 위해 패스
            if(neighbor == parent) continue;

            total[neighbor] = total[target] - count[neighbor] + (distance.length - count[neighbor]);

            calculateDist(neighbor, target, total);

        }

    }
}