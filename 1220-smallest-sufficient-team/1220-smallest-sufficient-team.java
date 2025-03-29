class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int totalSkills = req_skills.length;
        int totalPeople = people.size();
        
        // req_ skill 순서대로 비트 마스크를 만듬
        Map<String, Integer> skillMask =  new HashMap<>();
        for(int i = 0; i < totalSkills; i++) {
            skillMask.put(req_skills[i], 1<< i);
        }

        // 사람마다 각각의 스킬을 비트마스크로 만듬
        int[] peopleMask =  new int[totalPeople];
        for(int i = 0; i < totalPeople; i++) {
            int mask = 0;

            for(String skill : people.get(i)) {
                mask |= skillMask.get(skill); 
            }

            peopleMask[i] = mask;
        }

        // DP 설정
        int[] dp = new int[1 << totalSkills];

        // 초기화
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 사람 기록  설정
        Map<Integer, List<Integer>> team = new HashMap<>();

        // dp 진행
        for(int i = 0; i < totalPeople; i++) {
            int currSkillMask = peopleMask[i];

            // 0 부터( 1 << n)-1 까지 돌면서 dp 과정상 등록된 값이 있다면
            // 다시 더해서 해당 값의 인원  수를 추가
            for(int mask = (1 <<  totalSkills)-1 ;  mask >= 0; mask--) {
                // dp 에 사람들의 조합으로 만들어져 있을때 = -1이 아닐떄
                    // 위 사람의 skillMask와 mask 조합도 dp 값을 갱신하기 위해 없거나
                    // 새 조합의 dp 값이 존재하면 -> 새로 등록할 dp[mask] + 1 명보다 더 많은 조합이 이미 등록되 어있다면 -> 새로 갱신해야함
                if(dp[mask] != -1) {
                    int newSkillMask = currSkillMask | mask;
                    if(dp[newSkillMask] == -1 || 
                    dp[newSkillMask] > dp[mask] + 1) {
                        dp[newSkillMask] = dp[mask] + 1;

                        // 팀 새로운 팀으로갱신
                        List<Integer> newTeam = new ArrayList<>(team.getOrDefault(mask, new ArrayList<>()));
                        newTeam.add(i);
                        team.put(newSkillMask, newTeam);
                    }                    
                }
            }
        }

        // 가장 마지막 팀멤버 리스트
        List<Integer> resultList = team.get((1 << totalSkills) - 1);

        // Array로 반환
        return resultList.stream().mapToInt(Integer::valueOf).toArray();
    }
}