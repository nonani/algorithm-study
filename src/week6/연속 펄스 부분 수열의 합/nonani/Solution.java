/*
## ✏️ [프로그래머스] 연속 펄스 부분 수열 합
📶 문제 난이도
Lv. 3

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/131701

⏱️ 풀이 시간
20분

✅ 풀이 근거
순환하는 배열의 부분합이라는 점이 참신했다. 이걸 어떻게 한줄의 배열로 표현할지 고민하다보니
자연스럽게 일반적인 한줄짜리 문제로 바꿔서 풀 수 있었다.

 */

import java.util.*;

class Solution {
    public long solution(int[] s) {
        long local1 = s[0], global1 = s[0];
        long local2 = -s[0], global2 = -s[0];

        for (int i = 1; i < s.length; i++) {
            int pulse = (i % 2 == 0) ? s[i] : -s[i];

            local1 = Math.max(local1, 0) + pulse;
            local2 = Math.max(local2, 0) - pulse;

            global1 = Math.max(global1, local1);
            global2 = Math.max(global2, local2);
        }
        return Math.max(global1, global2);
    }
}


/*
✅ 풀이 2 근거 (prefix sum)

0부터 시작한 특정 구간합의 최대 값이 되는 지점을 a라 하자.
동시에 0부터 시작한 특정 구간합의 최대 값이 되는 지점을 b라 하자.

예를 들어 [1, 3, -4]라 하면
a는 인덱스 1이다. 왜냐하면 1+3 이 가장 큰 구간합이니까
b는 인덱스 2이다. 1+3-4=0으로 가장 작은 구간합이니까

부분 구간합의 최대값은 a<b 일땐 [a, b], a>b 일땐 [b, a]의 구간합이다.

"첫 원소부터 시작하는 구간"이 빠지지 않는다.
 */
class SolutionPrefixSum {
    public long solution(int[] s) {
        long prefix = 0;   // b[0..i]의 누적합
        long max = 0;      // prefix 최댓값 (빈 접두사 0 포함)
        long min = 0;      // prefix 최솟값 (빈 접두사 0 포함)

        for (int i = 0; i < s.length; i++) {
            prefix += (i % 2 == 0) ? s[i] : -s[i];  // 부호 입혀서 누적
            max = Math.max(max, prefix);
            min = Math.min(min, prefix);
        }

        // 최대 구간합 = max - min (max 위치가 min보다 앞이어도,
        // 그 경우 부호 반전 트랙이 같은 값을 만들므로 항상 유효)
        return max - min;
    }
}


