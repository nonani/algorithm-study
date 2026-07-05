/*
## ✏️ [프로그래머스] 귤 고르기
📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/138476

⏱️ 풀이 시간
1시간

✅ 풀이 근거
귤 크기별 개수를 세고, 개수가 많은 크기부터 선택하면 같은 k개를 고르면서 필요한 크기 종류 수를 최소화할 수 있다.
*/

import java.util.*;

class Orange {
    int size; // 이 오렌지의 크기
    int count; // 이 녀석이 등장한 횟수
    Orange(int size, int count) {
        this.size = size ; this.count = count;
    }
}

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> info = new HashMap<>();   // 출석부 map<귤의 크기, 이 크기의 귤의 고유 아이디(리스트에 해당 귤이 위치할 인덱스)>
        ArrayList<Orange> list = new ArrayList<>();     // 오렌지 리스트

        for(int oSize: tangerine) {
            Integer id = info.get(oSize);
            if(id == null) {
                // 만약 처음 등장한 id이면 출석부에 등록 하고 리스트에 추가
                info.put(oSize, list.size());
                list.add(new Orange(oSize, 1));
                continue;    
            }
            // 만약 이전에 등장했던 오렌지면 출석부에서 이게 리스트의 어떤 인덱스에 있는지 가져왔으니
            list.get(id).count++;
        }

        list.sort((o1, o2) -> {
            return o2.count - o1.count;
        });
        
        for(Orange o: list) {
            k-=o.count;
            answer++;
            if(k <= 0)
                break;
            // System.out.println(o.size +" " + o.count);
        }
        return answer;
    }
}
