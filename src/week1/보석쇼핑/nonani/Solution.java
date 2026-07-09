/*
## ✏️ [프로그래머스] 보석 쇼핑
📶 문제 난이도
Lv. 3

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/67258

⏱️ 풀이 시간
1시간

✅ 풀이 근거
구간이 일정하다면 딱 슬라이딩 윈도우 유형의 문제였는데 구간 크기가 바뀌어야해서 투포인터로 중간에 수정했다.
중간에 자꾸 IndexOutOfBound 예외가 계속 발생했었는데 right++ 하는 위치가 잘못되었었다..
*/


import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        int N = gems.length;
        Set<String> totalGemSet = new HashSet<>();
        HashMap<String, Integer> gemInfo = new HashMap<>();

        for(String g: gems) {
            totalGemSet.add(g);
        }

        int X = totalGemSet.size(); // 보석의 개수
        int left = 0;
        int right = 0;
        int[] answer = {0, 1000000};

        while(right < N ) {
            String addedGem = gems[right];
            gemInfo.put(addedGem, gemInfo.getOrDefault(addedGem, 0) + 1);
            while(gemInfo.size() == X) {
                if(answer[1] - answer[0] > right - left) {
                    answer[0] = left+1;
                    answer[1] = right+1;
                }
                String deletedGem = gems[left];

                gemInfo.put(deletedGem, gemInfo.get(deletedGem) - 1);
                if(gemInfo.get(deletedGem) == 0)
                    gemInfo.remove(deletedGem);
                left++;
            }
            right++;
        }
        return answer;
    }
}