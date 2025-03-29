class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int skillNum = req_skills.length;
        int pplNum = people.size();

        // Map<스킬, 스킬 마스크> 만들기
        Map<String, Integer> skillMask = new HashMap<>();
        for(int i = 0; i < skillNum; i++) {
            skillMask.put(req_skills[i], 1 << i);
        }

        // 사람 스킬 -> 위 맵 사용해서 마스크 만들기
        int[] pplMask = new int[pplNum];
        for(int i = 0; i < pplNum; i++) {
            int mask = 0;
            for(String skill : people.get(i)) {
                mask |= skillMask.get(skill); 
            }
            pplMask[i] = mask;
        }

        // Dp  생성 + 초기화
        int[] dp = new int[1 << skillNum];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // Map<마스크,  사람인덱스 리스트> 생성
        Map<Integer, List<Integer>> group = new HashMap<>();

        // DP 돌면서 최솟값갱신
            // 최솟값 갱신시, 해당 마스크당 사람인덱스 List 갱신
        for(int i = 0; i < pplNum; i++) {
            int currSkillMask = pplMask[i];

            for(int mask = (1 << skillNum) -1; mask>=0; mask--) {
                int newSkillMask = currSkillMask | mask;
                // dp에 mask 계산이 되어 있어야
                    // 새로 갱신될 마스크 DP 값이 계산이 안되거나
                 // 새로 갱신될 마스크 DP 의 이전 값이  dp[mask] +1 보다 크면 갱신 (최솟값 갱신이므로)
                if(dp[mask] != -1) {
                    if(dp[newSkillMask] == -1 ||
                    dp[newSkillMask] > dp[mask] + 1) {
                        dp[newSkillMask] = dp[mask] + 1;

                        // 팀 새롭게 만들기
                        List<Integer> newGroup = new ArrayList<>(group.getOrDefault(mask, new ArrayList<>()));
                        newGroup.add(i);
                        group.put(newSkillMask, newGroup);
                    }  

                }

            }
        }

        // 1<<skillNum -1 번째 인덱스 값에 해당하는 리스트 구하기
        List<Integer> result = group.get((1 << skillNum)-1);

        // 리스트 Array로 만들어 반환
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}