class Solution {
    public int maxStudents(char[][] seats) {
        int rows = seats.length;
        int cols = seats[0].length;

        // 행별로 비트 마스크 만들기 Map
        Map<Integer, List<Integer>> validSeatMasks = new HashMap<>();

        for(int row = 0; row < rows; row++) {
            List<Integer> validList = new ArrayList<>();
            // 모든 마스크 대조
            for(int mask = 0; mask < (1 << cols); mask++) {
                if(isValidMask(seats[row], mask)) {
                    validList.add(mask);
                }
            }
            validSeatMasks.put(row, validList);
        }

        // DP 테이블 생성, 값은 학생의 수
        int[][] dp = new int[rows][1 << cols];

        // 초기값 설정 -> 학생이 앉지 않을 때가 0이기 때문에 계산 이전의 의미인 -1 등록
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // 1열 dp 학생수 등록
        for(int mask : validSeatMasks.get(0)) {
            dp[0][mask] = Integer.bitCount(mask);
        }

        // 비트 마스크를 하나씩 반복하면서 유효한 DP에 넣기
        for(int i = 1; i < rows; i++) {
            for(int currMask : validSeatMasks.get(i)) {
                for(int prevMask : validSeatMasks.get(i - 1)) {
                    if(isMatch(currMask, prevMask, cols)) {
                        dp[i][currMask] = Math.max(dp[i][currMask], dp[i - 1][prevMask] + Integer.bitCount(currMask));
                    }
                }
            }
        }

        // 최대 경우의 수 return
        int result = 0;
        for(int mask : validSeatMasks.get(rows - 1)) {
            result = Math.max(result, dp[rows - 1][mask]);
        }

        return result;
    }

    public boolean isValidMask(char[] seats, int mask) {
        int seatMask = 0;
        for(char c : seats) {
            seatMask <<= 1;
            int bit = 0;
            if (c == '.') bit = 1;
            seatMask |= bit;
        }

        // mask와 비교 연산
        if((mask & seatMask) != mask) return false;
        if( ( (mask >> 1) & mask) != 0) return false;

        return true;
    }

    public boolean isMatch(int currMask, int prevMask, int cols) {
        for(int i = 0; i < cols; i++) {
            if( ((1 << i) & currMask) != 0) {
                if(i > 0 && ((1 << (i - 1)) & prevMask) != 0) return false;
                if(i < cols - 1 && ((1 << (i + 1)) & prevMask) != 0) return false;
            }
        }
        return true;
    }
}
