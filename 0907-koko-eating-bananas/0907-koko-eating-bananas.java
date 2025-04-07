class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int k = 0;

        // Sorting Array
        Arrays.sort(piles);

        // left right
        int left = 1;
        int right = piles[n-1];

        // binary search
        while(left <= right) {
            int mid = left + (right - left)/2;

            // consume piles
            int hours = 0;
            for(int pile : piles) {
                hours += Math.ceil((double)pile/mid);
            }

            if(hours > h) {
                left = mid + 1;
            }

            if(hours <= h) {
                right = mid - 1;
                k = mid;
            }
        }

        return k;
    }
}