import java.util.*;

class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        
        boolean[][] visited = new boolean[x+1][y+1];
        return bfs(x, y, visited, target);
    }

    public boolean bfs(int x, int y, boolean[][] visited, int target) {

        // 큐만들기
        Queue<int[]> queue = new LinkedList<>();

        // 초기설정
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        // 1씩 추가하면서 확인
        while(!queue.isEmpty()) {
            // queue 순회 하면서 BFS
            int size =  queue.size();
            for(int i = 0; i < size; i++) {
                int[] node = queue.poll();
                int firstBucket = node[0];
                int secondBucket = node[1];

                // target에 부합
                if(firstBucket == target ||
                secondBucket == target ||
                firstBucket + secondBucket == target) return true;

                // queue 넣기 가능한 경우
                // 첫번째만 가득
                // 두번째만 가득
                // 첫번째만 비우기
                // 두번째만 비우기
                // 첫번째-> 두번째 가득
                // 두번쨰 -> 첫번째 가득
                int[][] tempArray = {
                    {firstBucket, 0},
                    {0, secondBucket},
                    {x, secondBucket},
                    {firstBucket, y},
                    {firstBucket - Math.min(firstBucket, y - secondBucket), secondBucket + Math.min(firstBucket, y - secondBucket)},
                    {firstBucket + Math.min(secondBucket, x - firstBucket), secondBucket - Math.min(secondBucket, x - firstBucket)}
                };
                
                for(int[] temp : tempArray) {
                    int a = temp[0];
                    int b = temp[1];

                    if(a>=0 && b>=0 && !visited[a][b]) {
                        queue.add(new int[]{a,b});
                        visited[a][b] = true;
                    }
                }
            }
        }
        
        return false;
    }
}