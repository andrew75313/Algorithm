import java.util.*;

public class Solution {

    // 절대 불변할 문제의 조건들
    private static final int MOD = 1000000007;
    private static final int[] PRIMES = {2,3,5,7,11,13,17,19,23,29};

    public static int numberOfGoodSubsets(int[] nums) {

        // 소수 마스크를 생성 Map<소수인덱스, 해당 마스크>
        // 30이하의 수이기 때문에 미리 만들기
        Map<Integer, Integer> primeMasks = new HashMap<>();
        for(int i = 1; i <= 30; i++) {
             int mask  = 0;

            for(int j = 0; j < PRIMES.length; j++) {
                // 숫자는  소수로 나눠져야
                // 똑같은 소수로 두번 나눠지면 0 곧바로 break
                if( (i % PRIMES[j]) == 0) {
                    if( (i % (PRIMES[j]*PRIMES[j])) == 0) {
                        mask = 0;
                        break;
                    }
                    mask |= (1<<j);
                }
            }
            primeMasks.put(i, mask);
        }

        // nums  숫자
        int[] count = new int[31];
        for(int num : nums) {
            count[num]++;
        }

        // 중복제거 
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num); 
        }

        

        // DP 설정
        int[] dp = new int[1<<PRIMES.length];

        // 초기값 설정
        dp[0] = 1;

        // num 하나씩 반복하면서 DP에 넣기
        for(int num : numsSet) {
            int mask = primeMasks.get(num);

            // primeMask가 0 이면 그냥 넣지않기
            // num 에 없는  아니면 그냥 패스 
            if( mask == 0) continue;

            // 11111.. 에서 부터 하나씩 줄여가면서 진행 
             for(int j = (1 << PRIMES.length)-1 ; j >= 0; j--) {
                // 마스크가 겹칠때만 dp에 갱신 
                if( (mask & j) == 0) {
                    dp[mask | j] = (dp[mask | j] + (int)((long)dp[j] * count[num] % MOD)) % MOD;

                }
             }
        }

        // 숫자를 순차적으로 처리
        int result = 0;
        for (int i = 1; i < (1 << PRIMES.length); i++) {
            result = (result + dp[i]) % MOD;
        }

        // 숫자 1도 포함되어있다면 굿셋이기 때문에
        // 1이 포함된 것 반큼 result를 배로 늘려줘야함
        for(int i = 0; i < count[1]; i++) {
            result = ( result * 2 ) % MOD; 
        }

        return result;
    }
}