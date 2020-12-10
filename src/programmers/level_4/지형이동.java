package programmers.level_4;

import java.util.*;

import static java.util.Comparator.*;

/**
 * lv4
 * # 그래프 #kruskal #unionfind
 * # 성공
 * # 1시간
 */
public class 지형이동 {

    public static void main(String[] args) {
        int[][] rand = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
        int h = 3;
        int n = new 지형이동().solution(rand, h);
        System.out.println(n);
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int[][] visit;
    static List<Ladder> ladders;
    static int nodeSize;


    public int solution(int[][] land, int height) {

        N = land.length;

        createGroup(land, height);
        createDiff(land, height);
        return kruskal();
    }

    void createDiff(int[][] land, int height) {

        ladders = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int v = land[i][j];

                for (int d = 0; d < 4; d++) {
                    if (d == 0 || d == 2) continue;

                    int mx = i + dx[d];
                    int my = j + dy[d];
                    if (mx < N && my < N) {
                        int mv = land[mx][my];
                        if (Math.abs(mv - v) > height && visit[i][j] != visit[mx][my]) {
                            ladders.add(new Ladder(new Point(i, j, v), new Point(mx, my, mv), Math.abs(mv - v)));
                        }
                    }
                }
            }
        }
    }

    void createGroup(int[][] land, int h) {

        visit = new int[N][N];
        int key = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] == 0) dfs(land, i, j, h, key++);
            }
        }

        nodeSize = key;
    }

    void dfs(int[][] land, int x, int y, int h, int key) {

        Queue<Point> q = new LinkedList<>();
        Point first = new Point(x, y, land[x][y]);
        q.add(first);
        visit[x][y] = key;

        while (!q.isEmpty()) {

            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int mx = p.x + dx[d];
                int my = p.y + dy[d];
                if (mx < N && my < N && mx >= 0 && my >= 0) {
                    int mv = land[mx][my];
                    if (visit[mx][my] == 0 && Math.abs(mv - p.v) <= h) {
                        visit[mx][my] = key;
                        q.add(new Point(mx, my, mv));
                    }
                }
            }
        }
    }

    int kruskal() {

        int[] nodes = new int[nodeSize - 1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = i;
        }

        int cnt = 0;

        ladders.sort(comparingInt(o -> o.cost));
        int i = 0;
        while (!isAllEquals(nodes)) {
            Point a = ladders.get(i).x;
            Point b = ladders.get(i).y;
            int groupX = visit[a.x][a.y] - 1;
            int groupY = visit[b.x][b.y] - 1;
            int start = Math.min(groupX, groupY);
            int end = Math.max(groupX, groupY);

            if (nodes[start] != nodes[end]) {
                updatesNodes(nodes, nodes[start], nodes[end]);
                cnt += ladders.get(i).cost;
            }

            i++;
        }

        return cnt;
    }

    private void updatesNodes(int[] nodes, int start, int end) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == end) nodes[i] = start;
        }
    }

    boolean isAllEquals(int[] nodes) {

        for (int i = 0; i < nodes.length - 1; i++) {
            if (nodes[i] != nodes[i + 1]) return false;
        }

        return true;
    }

    class Point {
        int x;
        int y;
        int v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        @Override
        public String toString() {
            return "{" +
                    "" + x +
                    "," + y +
                    '}';
        }
    }

    class Ladder {
        Point x, y;
        int cost;

        public Ladder(Point x, Point y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cost=" + cost +
                    '}';
        }
    }
}
