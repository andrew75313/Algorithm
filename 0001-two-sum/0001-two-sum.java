class Solution {
    public int[] twoSum(int[] nums, int target) {
    int[] output = new int[2];

    for(int i = 0; i < nums.length; i++) {
        int i_var = nums[i];

        for(int j = i + 1; j < nums.length; j++) {
            int j_var = nums[j];
                       
            if(i_var + j_var == target) {
                output[0] = i;
                output[1] = j;
                break;
            }
        
        }
    }

    return output;
    }
}
