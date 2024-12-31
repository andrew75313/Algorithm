import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {

        // amount 0일경우 0 바로 반환
        if(amount == 0) return 0;

        boolean[] visited = new boolean[amount+1];

        return bfs(coins, amount, visited);
    }

    public int bfs(int[] coins, int amount, boolean[] visited) {
        // queue 생성
        Queue<int[]> queue = new LinkedList<>();

        // 초기 설정
        visited[amount] = true;
        queue.add(new int[]{0, amount});


        // while문
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentCount = current[0];
            int currentAmount = current[1];
            
            // 동전 한번씩 쓰기
            for (int coin : coins) {
                int nextAmount = currentAmount - coin;

                // amount 가 0이 되었을 때
                if(nextAmount == 0) return currentCount + 1;
                
                // amout 가 0이 아닐 때
                // 해당 amount가 방문이 안되어있을 때
                // 다음 큐 등록
                if(nextAmount > 0 &&
                !visited[nextAmount]) {
                    visited[nextAmount] = true;
                    queue.add(new int[]{currentCount + 1 ,nextAmount});
                }
            }
        }

        // while문이 끝났을 때,
        // amount가 여전히 0이 아닐때 실패
        return -1;
    }
}