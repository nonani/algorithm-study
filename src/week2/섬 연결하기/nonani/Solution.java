/*
## ✏️ [프로그래머스] 섬 연결하기
📶 문제 난이도
Lv. 3

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/42861?language=java

⏱️ 풀이 시간
5분

✅ 풀이 근거
//MST 구하는 문제
// Prim으로 풀어보자
// Prim은 엣지수가 많은 상황에서 이득을 볼 수 있는 알고리즘
// 만약 엣지수가 적은 상황에서는 크루스칼보단 성능이 떨어지는 상황이 나올 가능성이 높지만 그런 세부 지시사항은 없으니 뭘 선택하든 상관은 없을듯
*/





import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        int cnt = 0;    // 방문한 정점 개수를 카운트, N이면 다 방문한거니까 더 진행할 필요가 없음

        //int[0] : 다음으로 방문할 노드 번호, int[1] : 그 노드까지의 이동하는 거리(간선 거리)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });

        ArrayList<int[]> graph[] = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<int[]>();
        }

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        //임의의 정점하나를 고른다.
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty() && cnt < n) {
            // 현재 pq에 있는 후보 정점 중 거리가 가장 짧은 걸 고른다.
            int[] node = pq.poll();
            int cur = node[0];
            int dist = node[1];

            if (visited[cur])
                continue;

            visited[cur] = true;
            cnt++;
            answer += dist;

            // 다음 정점 후보를 고른다.
            for (int i = 0; i < graph[cur].size(); i++) {
                int[] nextNode = graph[cur].get(i);
                int next = nextNode[0];
                int cost = nextNode[1];
                if (visited[next])
                    continue;
                pq.add(new int[]{next, cost});
            }
        }
        return answer;
    }
}