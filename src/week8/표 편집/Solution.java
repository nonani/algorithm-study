/*
## ✏️ [프로그래머스] 표 편집
📶 문제 난이도
Lv. 3

🔗 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/81303

⏱️ 풀이 시간
50분

✅ 풀이 근거
생 구현 문제였다.
그런데 삭제 내역을 스택으로 관리하면 보다 컨트롤 z하기 쉬울 것 같아서 이건 스택으로 관리해야겠따고 생각했따.
*/

import java.util.*;

class Node {
    int index;
    int prev;
    int next;

    Node(int index, int prev, int next) {
        this.index = index;
        this.prev = prev;
        this.next = next;
    }
}

class Solution {


    public String solution(int n, int k, String[] cmds) {

        Node[] nodes = new Node[n];

        // 연결 리스트 구성
        for (int i = 0; i < n; i++) {
            int prev = i - 1;
            int next = i + 1;

            if (i == n - 1)
                next = -1;

            nodes[i] = new Node(i, prev, next);
        }

        Stack<Node> stack = new Stack<>(); // 지워진 애들
        int cur = k;

        for (String cmd : cmds) {
            char oper = cmd.charAt(0);

            // 위로 이동
            if (oper == 'U') {

                int count = Integer.parseInt(cmd.substring(2));

                while (count-- > 0) {
                    cur = nodes[cur].prev;
                }
            }

            // 아래로 이동
            else if (oper == 'D') {

                int count = Integer.parseInt(cmd.substring(2));

                while (count-- > 0) {
                    cur = nodes[cur].next;
                }
            }

            // 삭제
            else if (oper == 'C') {

                int prev = nodes[cur].prev;
                int next = nodes[cur].next;

                stack.push(new Node(cur, prev, next));

                // 앞 노드와 뒤 노드를 서로 연결
                if (prev != -1) {
                    nodes[prev].next = next;
                }

                if (next != -1) {
                    nodes[next].prev = prev;
                }

                // 커서 이동
                if (next != -1)  // 다음 행이 있으면 다음 행
                    cur = next;
                else  // 마지막 행을 삭제했다면 이전 행
                    cur = prev;

            }

            // 복구
            else if (oper == 'Z') {

                Node deleted = stack.pop();

                int index = deleted.index;
                int prev = deleted.prev;
                int next = deleted.next;

                // 삭제됐던 노드의 연결 정보 복구
                nodes[index].prev = prev;
                nodes[index].next = next;

                if (prev != -1)
                    nodes[prev].next = index;
                if (next != -1)
                    nodes[next].prev = index;

            }
        }

        char[] answer = new char[n];
        Arrays.fill(answer, 'O');

        // 아직 stack에 남아있는 노드 = 최종적으로 삭제된 노드
        while (!stack.isEmpty()) {
            answer[stack.pop().index] = 'X';
        }

        return new String(answer);
    }
}