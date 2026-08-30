/*
## ✏️ [프로그래머스] 할인 행사

📶 문제 난이도
Lv. 2

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/131127

⏱️ 풀이 시간
30분

✅ 풀이 근거
// n, x가 100 보다 세제곱도 가능 따라서 브루트포스 문제
*/

import java.util .*;

// class Node{
//     int x, y, depth;
//     ArrayList<Node> paths;
//     public Node(int x, int y, int depth, ArrayList<Node> paths) {
//         this.x = x;
//         this.y = y;
//         this.depth = depth;
//         this.paths = paths;
//     }
// }

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int[][][] visited = new int[30000][101][101]; // [sec][x][y] sec초에 (x, y)를 몇회 방문함

        for (int[] route : routes) {

            int sec = 0;

            int[] start = points[route[0] - 1];

            int fromY = start[0];
            int fromX = start[1];

            // 최초 시작 위치만 한 번 기록
            visited[sec][fromX][fromY]++;

            for (int node = 0; node < route.length - 1; node++) {

                int[] to = points[route[node + 1] - 1];

                int toY = to[0];
                int toX = to[1];

                while (fromY != toY) {
                    if (fromY < toY) {
                        fromY++;
                    } else {
                        fromY--;
                    }

                    sec++;
                    visited[sec][fromX][fromY]++;
                }

                while (fromX != toX) {
                    if (fromX < toX) {
                        fromX++;
                    } else {
                        fromX--;
                    }

                    sec++;
                    visited[sec][fromX][fromY]++;
                }
            }
        }

        // for (int s = 0; s < 8; s++) {
        //     printArr(visited[s]);
        // }

        for (int[][] s : visited) {
            for (int[] i : s) {
                for (int j : i) {
                    if (j > 1) {
                        // 방문횟수가 1보다 크면 두개 이상의 로봇이 같은 초에 방문했으므로 충돌했다는 뜻
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    private void printArr(int[][] arr) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
}