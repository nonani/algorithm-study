/*
## ✏️ [프로그래머스] 할인 행사

📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/131127

⏱️ 풀이 시간
30분

✅ 풀이 근거
단순하게 생각하면 1~100,000일 동안 want의 길이 일 연속으로 되는지 확인하면 되어서
최악의 경우 100,000 * 10이다.
그러나 슬라이딩 윈도우를 적용해서 want의 길이 만큼의 크기의 윈도우를 한칸 옮길 때마다 제일 왼쪽 과일은 빼고 새로 추가되는 오른쪽 과일은 포함시키는 식으로 하면 100,000만으로 가능하다.
*/


import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int N = 0;
        for (int w : number)
            N += w;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            // 원하는 물품과 원하는 수량을 Map에 저장해둔다.
            map.put(want[i], number[i]);
        }

        // 우선 0일에 가입한 시나리오
        for (int i = 0; i < N; i++) {
            if (map.get(discount[i]) == null) continue;
            map.put(discount[i], map.get(discount[i]) - 1);
        }

        // 이때 몇개나 할인 받을 수 있는지 체크
        int cnt = 0;
        for (String key : map.keySet()) {
            if (map.get(key) == 0) cnt++;
        }

        if (cnt == want.length) answer++;

        // for(String k : want) {
        //     System.out.println(k +" : " + map.get(k) +" | ");
        // }
        // System.out.println(cnt+"\n");


        for (int i = N; i < discount.length; i++) {

            // 슬라이딩 윈도우
            String leftFruit = discount[i - N];
            String rightFruit = discount[i];

            // 사고 싶은거에 포함 됐던거면
            if (map.get(leftFruit) != null) {
                if (map.get(leftFruit) == 0) cnt--;
                map.put(leftFruit, map.get(leftFruit) + 1);
                if (map.get(leftFruit) == 0) cnt++;

            }

            if (map.get(rightFruit) != null) {
                if (map.get(rightFruit) == 0) cnt--;
                map.put(rightFruit, map.get(rightFruit) - 1);
                if (map.get(rightFruit) == 0) cnt++;
            }


            // for(String k : want) {
            //     System.out.println(k +" : " + map.get(k) +" | ");
            // }
            // System.out.println(cnt+"\n");

            if (cnt == want.length) answer++;

        }
        return answer;
    }
}