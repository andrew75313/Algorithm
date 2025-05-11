class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;

        // sort envelopes
        // width ASC -> height DESC
        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1]; 
            } else {
                return a[0] - b[0];
            }
        });


        // find LIS of Height
        List<Integer> lis = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int height = envelopes[i][1];

            int index = Collections.binarySearch(lis, height);

            // no val
            if(index < 0) index = Math.abs(index) - 1;
 
            // more than lis
            if(index >= lis.size()) lis.add(height);

            // less than lis
            if(index < lis.size()) lis.set(index, height);

        }

        return lis.size();
    }
}