package practice;

import java.util.*;

public class 다익스트라 {

    /*
     * 선형탐색 버전 (O^2)
     */
    public static class Version1 {

        static int INF = 1000000;

        static int[][] map = new int[][]{
                {0, 2, 5, 1, INF, INF},
                {2, 0, 3, 2, INF, INF},
                {5, 3, 0, 3, 1, 5},
                {1, 2, 3, 0, 1, INF},
                {INF, INF, 1, 1, 0, 2},
                {INF, INF, 5, INF, 2, 0}
        };

        static int N = 6;
        static int[] dist;
        static boolean[] visit;

        public static void main(String[] args) {

            // init
            dist = new int[N];
            visit = new boolean[N];

            // run
            dijkstra(0);

            // result
            System.out.println(Arrays.toString(dist));
        }

        private static int getMinIdx() {

            int min = Integer.MAX_VALUE;
            int idx = 0;

            for (int i = 0; i < dist.length; i++) {
                if (dist[i] < min && !visit[i]) {
                    min = dist[i];
                    idx = i;
                }
            }


            return idx;
        }

        private static void dijkstra(int start) {

            // start -> i 비용 갱신
            for (int i = 0; i < dist.length; i++) {
                dist[i] = map[start][i];
            }

            // start 방문 처리
            visit[start] = true;

            // 최소 비용 갱신
            for (int i = 0; i < N - 1; i++) {
                int current = getMinIdx();
                visit[current] = true;
                for (int j = 0; j < N; j++) {
                    if (!visit[j]) {
                        if (dist[current] + map[current][j] < dist[j]) {
                            dist[j] = dist[current] + map[current][j];
                        }
                    }
                }
            }
        }
    }

    /**
     * 힙 버전
     * O (N * logN)
     */
    public static class Version2 {

        static int INF = 1000000;

        static int[][] map = new int[][]{
                {0, 2, 5, 1, INF, INF},
                {2, 0, 3, 2, INF, INF},
                {5, 3, 0, 3, 1, 5},
                {1, 2, 3, 0, 1, INF},
                {INF, INF, 1, 1, 0, 2},
                {INF, INF, 5, INF, 2, 0}
        };

        static int N = 6;
        static int[] dist;

        static PriorityQueue[] edges;

        public static void main(String[] args) {

            // init
            dist = new int[N];
            for (int i = 0; i < dist.length; i++) {
                dist[i] = INF;
            }

            initPriorityQueue();

            // run
            dijkstra(0);

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

        private static void initPriorityQueue() {
            // - edges[i]의 우선순위 큐는 i번째 노드와 연결된 간선의 리스트이며, 가중치 기준 오름차순으로 정렬된 형태
            edges = new PriorityQueue[N];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new PriorityQueue<Pair>();
            }

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {

                    int v = map[i][j];
                    if (v != 0 && v != INF)
                        edges[i].add(new Pair(j, map[i][j]));
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
}
