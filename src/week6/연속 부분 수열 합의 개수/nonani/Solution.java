/*
## ✏️ [프로그래머스] 연속 부분 수열 합의 개수
📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/131701

⏱️ 풀이 시간
10분

✅ 풀이 근거
순환하는 배열의 부분합이라는 점이 참신했다. 이걸 어떻게 한줄의 배열로 표현할지 고민하다보니
자연스럽게 일반적인 한줄짜리 문제로 바꿔서 풀 수 있었다.

 */

import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int N = arr.length;
        // 순환되는 리스트를 구현하기보단 그냥 뒤에 이어 붙여줌 ex) 1,2,3 -> 1,2,3,1,2
        int[] dp = new int[2 * N + 1];
        HashSet<Integer> sumSet = new HashSet<>();

        dp[0] = arr[0];
        for (int i = 1; i < 2 * N - 1; i++) {
            dp[i] = arr[i % N] + dp[i - 1];
        }

        for (int from = 0; from < N; from++) {
            for (int i = 0; i < N; i++) {
                int to = from + i;
                //구간 from ~ to 까지의 부분합 구하는 함수
                int sum = getSubSum(dp, from, to);

                // System.out.println(from+" ~ "+(to%N)+" : " +sum);
                sumSet.add(sum);
            }
        }


        return sumSet.size();
    }

    private int getSubSum(int[] dp, int from, int to) {
        if (from == 0)
            return dp[to];
        return dp[to] - dp[from - 1];
    }
}