/*
## ✏️ [프로그래머스] 가장 긴 팰린드롬
📶 문제 난이도
Lv. 3

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/12904

⏱️ 풀이 시간
10분

✅ 풀이 근거


 */

// 브루투포스 해도 될듯

// i를 시작, j를 끝으로 하는 애가 팰린드롬인지 체크하는건 j-i / 2 번 연산
// O(N^2) 으로 해결될 것이다.
// 아 N^3 이네
class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= 0; j--) {
                if (answer < j - i + 1 && check(s.substring(i, j + 1))) {
                    answer = j - i + 1;
                    // System.out.println(s.substring(i, j+1) +" " + answer);
                }
            }
        }

        return answer;
    }

    private boolean check(String str) {
        int l = 0;
        int r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}


