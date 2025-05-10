class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // bottom-up dp
        int[] dp = new int[n];

        // init : last row val
        for(int i = 0; i < triangle.get(n-1).size(); i++) {
            dp[i] = triangle.get(n-1).get(i);
        }

        // update dp
        // row reversed from last row - 1 index
        for(int r = (n-1)-1; r >= 0; r--) {
            
            // row - 1 values + mininum next Row value
            for(int i = 0; i <= r; i++) {
                dp[i] = triangle.get(r).get(i) + Math.min(dp[i], dp[i+1]);
            } 
        }


        return dp[0];
    }
}