class Solution {

    private final Map<Character, String> map = Map.of(
        '2', "abc", 
        '3', "def",
        '4', "ghi",
        '5', "jkl",
        '6', "mno", 
        '7', "pqrs",
        '8', "tuv",
        '9', "wxyz"
    );
    

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        // 아무것도 없을 때는 만들 empty 된 List로 반환
        if(digits.equals("")) return result;

        StringBuilder sb = new StringBuilder();

        int digitIndex = 0;

        dfs(digits, sb, result, digitIndex);

        return result;        
    }

    public void dfs(String digits, StringBuilder sb, List<String> result, int index) {
        // target 도달시 return
        if(index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        
        // map 에서 String 찾기
        char currChar = digits.charAt(index);
        String currString = map.get(currChar);

        // String Builder에 currString  붙이면서 재귀
        for(char c : currString.toCharArray()) {
            sb.append(c);
            dfs(digits, sb, result, index+1);

            // 다 진행이 되었다면 다시 백트래킹
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}