package programmers.level_3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * #그래프 #다익스트라
 */
public class 배달 {

    static int INF = 1000000;

    static int N;
    static int[] dist;
    static PriorityQueue[] edges;

    public int solution(int n, int[][] road, int K) {

        // init
        N = n;
        dist = new int[N];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = INF;
        }

        initPriorityQueue(road);

        // run
        dijkstra(0);
        return (int) Arrays.stream(dist).filter(v -> v <= K).count();
    }

    public static void main(String[] args) {

        int N = 5;
        int[][] road = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int K = 3;
        int result = new 배달().solution(N, road, K);
        System.out.println(result);

        // result
        System.out.println(Arrays.toString(dist));
    }

    private static void dijkstra(int start) {

        // 자기 자신의 거리는 0
        dist[start] = 0;

        // 시작점 넣기
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(start, 0));

        while (!q.isEmpty()) {

            Pair current = q.poll();
            int curr = current.node;
            int distance = current.v;

            // 이미 기존 거리가 더 작은 경우 skip
            if (dist[curr] < distance) continue;

            // start 노드에서 다른 노드로의 최단 경로 갱신
            Iterator it = edges[curr].iterator();
            while (it.hasNext()) {
                Pair next = (Pair) it.next();
                int nextNode = next.node;
                int nextDist = distance + next.v;

                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    q.add(new Pair(nextNode, nextDist));
                }
            }
        }
    }

    private static void initPriorityQueue(int[][] road) {

        edges = new PriorityQueue[N];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new PriorityQueue<Pair>();
        }

        for (int j = 0; j < edges.length; j++) {
            for (int i = 0; i < road.length; i++) {
                int n1 = road[i][0] - 1;
                int n2 = road[i][1] - 1;
                int v = road[i][2];

                if (n1 == j) {
                    edges[j].add(new Pair(n2, v));
                } else if (n2 == j) {
                    edges[j].add(new Pair(n1, v));
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int v;

        public Pair(int node, int v) {
            this.node = node;
            this.v = v;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.v, o.v);

        }
    }
}
