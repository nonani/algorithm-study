/*
## ✏️ [프로그래머스] 연속 펄스 부분 수열 합
📶 문제 난이도
Lv. 3

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/131701

⏱️ 풀이 시간
20분

✅ 풀이 근거
이진 탐색 문제인거 몰랐으면 못 풀었을 것 같다. dp로 풀어야한다고 생각하고 있어는데 아무리 생각해도
DP를 해도 적어도 N은 되어야 뭔가 될 것 같은데 N크기를 보면 logN아니면 안 풀리는 문제였어서 혼란스러웠었다....!!

그래도 이진탐색인걸 알고나니 좀 방법이 보였던 것 같다.
사실 전체 시간만 알면 그 시간에 n명을 처리할 수 있는지 아닌지 판별하는건 간단해서
이 방법으로 설정해봤다.
 */

class Solution {

    public long solution(int n, int[] times) {
        long answer = 0;
        long l = 1;
        long r = Long.MAX_VALUE;

        while(l <= r) {
            long mid = l + (r - l) / 2;

            if(getPersonNumInTime(times, mid, n)) {
                r = mid - 1;
                answer = mid;
            }

            else
                l = mid + 1;
        }
        return answer;
    }


    //주어진 시간동안 n명의 사람을 처리할 수 있는지 판별하는 함수
    private boolean getPersonNumInTime(int[] times, long total, int n){
        long count = 0;

        for (int t: times) {
            count += total / t;
            if(count >= n)
                return true;
        }
        return false;
    }
}

