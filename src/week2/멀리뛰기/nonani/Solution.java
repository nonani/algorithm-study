/*
## ✏️ [프로그래머스] 멀리 뛰기
📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/12914?language=java

⏱️ 풀이 시간
5분

✅ 풀이 근거
고민할 필요없이 점화식이 피보나치 수열이었다.
2칸 전에 뛴 경우 + 1칸 전 뛴 경우 더해주기만 하면 되는 문제
*/
class Solution {
    public long solution(int n) {

        int[] dp = new int[2001];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1234567;
        }
        return dp[n];
    }
}