/*
## ✏️ [프로그래머스] 튜플
📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/64065

⏱️ 풀이 시간
35분

✅ 풀이 근거
},{ 로 split하면 좋을 것 같다.
이후엔 길이를 기준으로 정렬한 뒤 visited set에 추가하다가 신규로 추가된 녀석을 배열에 추가는 식으로 하면 정렬도 깔끔하게 될 것 같았다.
*/

import java.util.*;

class Solution {
    public int[] solution(String s) {

        String[] subStrings = s.substring(2, s.length() - 2).split("\\},\\{");
        String str = "";
        Set<Integer> visited = new HashSet<>();
        int[] answer = new int[subStrings.length];
        int index = 0;

        Arrays.sort(subStrings, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        for (String sub : subStrings) {
            for (String number : sub.split(",")) {
                int value = Integer.parseInt(number);

                if (visited.add(value)) {
                    answer[index++] = value;
                    break;
                }
            }
        }
        return answer;
    }
}