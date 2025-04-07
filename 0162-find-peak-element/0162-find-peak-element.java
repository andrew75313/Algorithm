class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;

        // binary search -> find out index
        int index = 0;
        int left = 0;
        int right = n-1;

        while(left < right) {
            int mid = left + (right-left)/2;

            if(nums[mid] < nums[mid + 1]) {
                left = mid + 1;
                // then, moved left can be the answer
                index = left;
            }

            // right can be directly the answer so no -1
            if(nums[mid] > nums[mid + 1]) {
                right = mid;
                // right can be the answer
                index = right;
            }
        }

        return index;
    }
}