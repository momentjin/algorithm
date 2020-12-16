package programmers.level_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * #구현
 * 실패
 * 실패 사유
 * - 문제를 푸는 효과적인 방법에 대한 좀 더 남다른 사고방식이 필요해 보인다.
 */
public class 트리트리오중간값 {


    public static void main(String[] args) {
        int n = 4;
        int[][] e = {{1, 2}, {2, 3}, {3, 4}};
        int a = new 트리트리오중간값().solution(n, e);
        System.out.println(a);
    }

    public int solution(int n, int[][] edges) {

        ArrayList<Integer>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }

        int s = 1;
        int[] result = bfs(list, s, n);
        for (int i = 1; i <= n; i++) {
            if (result[i] > result[s]) s = i;
        }

        result = bfs(list, s, n);
        s = 1;
        for (int i = 1; i <= n; i++) {
            if (result[i] > result[s])
                s = i;
        }

        int max = Arrays.stream(result).max().getAsInt();
        int finalMax = max;
        int maxNum = (int) Arrays.stream(result).filter(i -> i == finalMax).count();

        // max가 2개이상일 때, 중간값 = max
        if (maxNum >= 2) return max;

        result = bfs(list, s, n);
        max = Arrays.stream(result).max().getAsInt();
        int finalMax1 = max;
        maxNum = (int) Arrays.stream(result).filter(i -> i == finalMax1).count();

        // max가 2개이상일 때, 중간값 = max
        if (maxNum >= 2) return max;

        return max - 1;
    }


    private int[] bfs(ArrayList<Integer>[] edges, int start, int n) {

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        visit[start] = true;

        while (!q.isEmpty()) {

            int node = q.poll();

            for (int next : edges[node]) {
                if (next == node) continue;
                if (visit[next]) continue;

                visit[next] = true;
                q.add(next);
                dist[next] = dist[node] + 1;
            }
        }

        return dist;
    }
}
