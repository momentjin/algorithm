package programmers.level_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * #DFS, #DFS
 * 성공
 * 소요시간 : 1시간 12분 ()
 *
 * 완전탐색 심화 문제
 * - 필수 방문 조건을 어떻게 처리해야할지가 핵심이었다.
 *
 * 함수 재귀 사용시 런타임 에러가 발생해서 스택으로 구현. (애초에 BFS로 했으면 더 간단했겠다)
 */
public class 동굴탐험 {

    public static void main(String[] args) {

        int n = 9;
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{8, 5}, {6, 7}, {4, 1}};

        boolean result = new 동굴탐험().solution(n, path, order);
        System.out.println(result);
    }

    static int[] waits;
    static boolean[] visit;
    static int size;

    public boolean solution(int n, int[][] path, int[][] order) {

        waits = new int[n];
        visit = new boolean[n];
        size = n;

        List<Integer>[] edges = new List[n];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] p : path) {
            edges[p[0]].add(p[1]);
            edges[p[1]].add(p[0]);
        }

        int[] prior = new int[n];
        for (int[] o : order) {
            prior[o[1]] = o[0];
        }

        if (prior[0] != 0) return false;

        visit[0] = true;
        for (int v : edges[0]) {
            visit(v, edges, prior);
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) return false;
        }

        return true;
    }

    // dfs (함수재귀)
    private void visit2(int v, List<Integer>[] edges, int[] prior) {

        if (visit[v])
            return;

        if (!visit[prior[v]]) {
            waits[prior[v]] = v;
            return;
        }

        visit[v] = true;
        visit(waits[v], edges, prior);

        for (int nextV : edges[v]) {
            visit(nextV, edges, prior);
        }
    }

    // dfs (stack 재귀)
    private void visit(int v, List<Integer>[] edges, int[] prior) {

        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()) {

            int next = stack.pop();

            if (visit[next])
                continue;

            if (!visit[prior[next]]) {
                waits[prior[next]] = next;
                continue;
            }

            visit[next] = true;
            for (int nextV : edges[next]) {
                stack.push(nextV);
            }

            stack.push(waits[next]);
        }
    }
}
