class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int oneDayTicket = costs[0];
        int sevenDayTicket = costs[1];
        int thirtyDayTicket = costs[2];

        List<Integer> dayList = Arrays.stream(days).boxed().toList();

        int lastDay = days[days.length - 1];

        // all Days
        int[] dp = new int[lastDay+1];
        
        // update dp checking all days
        for(int i = 1; i <= lastDay; i++) {
            // skip carrying past value if dayList doesn't contain i
            if(!dayList.contains(i)) {
                dp[i] = dp[i-1];
                continue;
            }

            // if dayList contains i,
            // compare 1, 7, 30 days sum -> update minimum
            if(dayList.contains(i)) {
                int oneDay = dp[i-1] + oneDayTicket;

                // if i-7 i-30 are under 0, refer them as 0
                int sevenDay = dp[Math.max(0, i-7)] + sevenDayTicket;          
                int thirtyDay = dp[Math.max(0, i-30)] + thirtyDayTicket;

                dp[i] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
            }

        }

        return dp[lastDay];
    }
}