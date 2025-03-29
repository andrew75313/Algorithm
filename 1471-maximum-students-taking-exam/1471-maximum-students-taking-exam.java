class Solution {
    public int maxStudents(char[][] seats) {
        int rows = seats.length;
        int cols = seats[0].length;

        // 행 마다 앉을 수 있는 모든 경우의 수 Map <행 인덱스, Bitmask List> 만들기
        Map<Integer, List<Integer>> validSeatMasks =  new HashMap<>();
        for(int index = 0; index < rows; index++) {
            List<Integer> validList = new ArrayList<>();

            // # . 을 비트마스크로 변경
            int seatMask = 0;
            for(char c : seats[index]) {
                seatMask <<= 1;

                int bit = 0;
                if(c == '.') bit =1;

                seatMask |= bit;
            }

            for(int mask = 0; mask < (1<<cols); mask++) {
                if(isValidMask(seatMask, mask)) {
                    validList.add(mask);
                }
            }
            validSeatMasks.put(index, validList);
        }

        // DP 설정 dp[행인덱스][Bitmask] value는 해당 비트마스크까지 최대경우
        int[][] dp = new int[rows][1 << cols];

        // 초기화 -1로 진행
        for(int[] row : dp) Arrays.fill(row, -1);

        // 인덱스 0 세팅
        for(int mask : validSeatMasks.get(0)) dp[0][mask] = Integer.bitCount(mask);

        // 1인덱스 row 부터 최댓값 갱신해서 넣기
        // 전 인덱스의 비트마스크와 비교(좌우)
        for(int index = 1; index < rows; index++) {
            for(int currMask : validSeatMasks.get(index)) {
                for(int prevMask : validSeatMasks.get(index-1)) {
                    if(isMatch(currMask, prevMask, cols)) {
                        // 현재 dp값 vs 전  dp 값에서 현재 마스크 1갯수 더한값 -> 킅것으로 현재 dp값을 갱신
                        dp[index][currMask] = Math.max(dp[index][currMask], dp[index -1][prevMask] + Integer.bitCount(currMask));
                    }
                }
            }
        }

        // rows-1 인덱스의 모든 마스크 중에 가장 큰 값 return 
        int maxStudents = 0;
        for(int mask : validSeatMasks.get(rows  - 1)) {
            maxStudents= Math.max(maxStudents, dp[rows-1][mask]);
        }
        
        return maxStudents;
    }

    public boolean isValidMask(int seatMask, int mask) {
        // mask와 비교
        // 빈자리 일경우 false
        if((mask & seatMask) != mask) return false;

        // 연속된 1이면 false
        if( ((mask >> 1) & mask) != 0) return false;

        return true;
    }

    public boolean isMatch(int currMask, int prevMask, int cols) {
        // 비트 하나씩 currMask 반복하면서 진행
        // -1, +1 인덱스의 prevMask 의 비트가 1이라면 false
        for(int i = 0; i < cols; i++) {
            // 비트 위치가 currMask와 맞을때
            if( ((1 << i) & currMask) != 0) {
                // prevMask 좌 확인
                if( 0 < i && (((1 << i-1) & prevMask) != 0)) return false;
                // 우 확인
                if( cols-1 > i && (((1 << i+1) & prevMask) != 0)) return false;
            }
        }
        return true;
    }
}