package programmers.level_3;

import java.util.*;

/**
 * 종류 : #DFS, #BFS
 * 시작일시 : 2020-11-09 13:59
 * 성공/실패 : 성공
 * 소요시간 : 35분
 *
 * 초반에 DFS가 아닌 백트래킹을 이용한 완전 탐색을 했기 때문에 시간 초과가 발생했던 것.
 */
public class 네트워크 {


    public static void main(String[] args) {
        int n = new 네트워크().solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}});
        System.out.println(n);
    }

    static boolean[] visit;

    // bfs
    public int solution(int n, int[][] computers) {

        int answer = 0;
        visit = new boolean[n];

        for (int j = 0; j < n; j++) {
            if (!visit[j]) {
                answer++;
                Queue<Integer> q = new LinkedList<>();
                q.add(j);

                while (!q.isEmpty()) {
                    int i = q.poll();
                    visit[i] = true;
                    for (int k = 0; k < computers.length; k++) {
                        if (computers[i][k] == 1 && !visit[k]) {
                            q.add(k);
                        }
                    }
                }
            }
        }

        return answer;
    }

    public int solution_dfs(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(computers, visit, i);
                answer++;
            }
        }
        return answer;
    }

    void dfs(int[][] computers, boolean[] chk, int start) {
        chk[start] = true;
        for (int i = 0; i < computers.length; i++) {
            if (computers[start][i] == 1 && !chk[i]) {
                dfs(computers, chk, i);
            }
        }
    }
}
