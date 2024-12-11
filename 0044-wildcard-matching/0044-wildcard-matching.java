import java.util.*;

class Solution {
    public boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        
        int i = 0;
        int j = 0;
        int asteriskIndex = -1;
        int matchIndex = -1;

        // s를 하나씩 넘기면서 p와 비교 통과만 확인, 나머지 false
        while (i < sLength) {
            // 문자 = 패턴 or ? (단, p 범위 안에서)
            if(j < pLength &&
            (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            // 아니면, 패턴이 *일경우, *위치 기록, 패턴다음으로, 통과 (단, p 범위 안에서)
            else if (j < pLength && 
            p.charAt(j) == '*') {
                asteriskIndex = j;
                matchIndex = i;
                j++;
            }
            // 아니면, asteriskIndex 있는지 확인 후, 비교, 패턴은 asteriskIndex 다음으로
            else if (asteriskIndex != -1) {
                j = asteriskIndex + 1;
                i = matchIndex + 1;
                matchIndex = i;
            } else {
                // 그 밖에 모든 사항에 대해 false
                return false;
            }
        } 

        // 남아있는 p가 *인지, j 올려주면서 전체 길이와 비교
        while (j < p.length() &&
        p.charAt(j) == '*') j++;

        return j == p.length();
    }
}