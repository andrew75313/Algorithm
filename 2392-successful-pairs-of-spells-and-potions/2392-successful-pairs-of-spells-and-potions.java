class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        // Sorting ASC
        Arrays.sort(potions);

        // Spell 당 BS 진행
        for(int i = 0; i < n; i++) {
            result[i] = countSuccess(spells[i], potions, success);
        }
        
        return result;
    }

    public int countSuccess(int spell, int[] potions, long success) {
        int m = potions.length;

        // potion  이분 탐색
        // 조건은 spell 곱한것이 success 보다 미만일때 끝
        int left = 0;
        int right = m-1;
        int index = m;

        while(left <= right) {
            int mid = left + (right - left)/2;

            long product = (long) potions[mid] * spell;

            if(product >= success) {
                index = mid;
                right = mid - 1;
            }

            if(product < success) {
                left = mid + 1;
            }
        }

        return m - index;
    }
}