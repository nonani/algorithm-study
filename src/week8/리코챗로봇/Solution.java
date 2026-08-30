/*
## ✏️ [프로그래머스] 리코챗 로봇

📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/169199

⏱️ 풀이 시간
30분

✅ 풀이 근거
Bfs 문제인데 그냥 상하좌우 한칸이 아니라 벽까지 쭉 밀어버리면 되는 문제
*/

import java.util.*;

class Node {
    int x, y, cnt;

    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Solution {
    int N, M;
    int[][] map;

    public int solution(String[] board) {
        int answer = 0;
        this.N = board.length;
        this.M = board[0].length();

        map = new int[N][M];
        int startX = 0;
        int startY = 0;
        int targetX = 0;
        int targetY = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = board[i].charAt(j);

                if (c == 'D') {
                    map[i][j] = 1;
                    continue;
                }
                if (c == 'G') {
                    targetY = i;
                    targetX = j;
                    continue;
                }
                if (c == 'R') {
                    startY = i;
                    startX = j;
                    continue;
                }
            }
        }


        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(startX, startY, 0));
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 4; d++) {
                Node next = getNext(cur, d);
                if (next.y >= 0 && next.y < N && next.x >= 0 && next.x < M && !visited[next.y][next.x]) {
                    visited[next.y][next.x] = true;
                    q.add(next);
                    if (next.y == targetY && next.x == targetX) return next.cnt;
                }
            }
        }

        return -1;
    }

    private Node getNext(Node cur, int d) {
        int x = cur.x;
        int y = cur.y;

        // 좌
        if (d == 0) {
            while (x > 0) {
                if (map[y][x - 1] == 1)
                    break;
                x--;
            }
        }

        // 우
        else if (d == 1) {
            while (x < M - 1) {
                if (map[y][x + 1] == 1)
                    break;
                x++;
            }
        }

        // 상
        else if (d == 2) {
            while (y > 0) {
                if (map[y - 1][x] == 1)
                    break;
                y--;
            }
        }

        // 하
        else {
            while (y < N - 1) {
                if (map[y + 1][x] == 1)
                    break;
                y++;
            }
        }
        return new Node(x, y, cur.cnt + 1);
    }
}