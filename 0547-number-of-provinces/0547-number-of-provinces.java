class Solution {

    // union find 를 활용하는 문제
    private int[] parent;
    private int[] rank;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        // Setting parent and rank
        parent = new int[n];
        rank = new int[n];

        // init Setting
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for(int i  = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(i != j && isConnected[i][j] == 1) {
                        union(i, j);
                }
            }
        }

        Set<Integer> result = new HashSet<>();
        for(int i = 0; i < parent.length; i++) {
            result.add(find(i));
        }
        
        return result.size();
    }

    // union 진행 
    public void union(int x, int y) {
        // 부모 root   찾기
        int rootX = find(x);
        int rootY = find(y);

        // 부모가 다를때, 세팅
        if(rootX != rootY) {
            // 서로의 부모 랭킹이 큰 방향으로 수정
            // 부모가 똑같다면
            if(rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            }

            if(rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            }

            // 같을 경우 랭킹조정
            if(rank[rootX] == rank[rootY]) {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // find 진행
    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }
}