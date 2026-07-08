/*
## ✏️ [프로그래머스] [1차] 캐시
📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/17680

⏱️ 풀이 시간
10분

✅ 풀이 근거
걍 LRU캐시를 하는건데 캐시 사이즈가 0인 경우 엣지케이스가 있었다.
코테였으면 아마 그냥 냈을 것 같다.
*/

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        //오래될 수록 뒤로 간다.
        LinkedList<String> list = new LinkedList<>();
        int curSize = 0;

        if(cacheSize == 0)
            return 5 * cities.length;

        for(String city : cities) {
            String c = city.toLowerCase();
            if(list.contains(c)) {
                list.remove(c);
                list.addFirst(c);
                answer+=1;
            }else {
                if(curSize < cacheSize) {
                    curSize++;
                    list.addFirst(c);
                }
                else {
                    list.removeLast();
                    list.addFirst(c);
                }
                answer+=5;
            }


        }
        return answer;
    }
}