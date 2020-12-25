package programmers.level_4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * #BFS
 * 소요 시간 : 30분
 */
class 게임맵최단거리 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dist;

    public static void main(String[] args) {

        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
        int n = new 게임맵최단거리().solution(maps);
        System.out.println(n);
    }

    public int solution(int[][] maps) {

        dist = new int[maps.length][maps[0].length];
        dist[0][0] = 1;
        return bfs(maps);
    }

    public int bfs(int[][] maps) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1));

        while (!q.isEmpty()) {

            Point curr = q.poll();

            if (curr.x == maps.length - 1 && curr.y == maps[0].length - 1) {
                return curr.v;
            }

            for (int i = 0; i < 4; i++) {

                int mx = dx[i] + curr.x;
                int my = dy[i] + curr.y;

                if (mx < 0 || my < 0 || mx >= maps.length || my >= maps[0].length) {
                    continue;
                }

                if (maps[mx][my] == 0)
                    continue;

                // 이미 거리가 정해져있고, 그 거리값이 갱신될값보다 작은 경우 pass
                if (dist[mx][my] != 0 && dist[mx][my] <= curr.v + 1) {
                    continue;
                }

                dist[mx][my] = curr.v + 1;
                q.add(new Point(mx, my, curr.v + 1));
            }
        }

        return -1;
    }

    class Point {
        int x, y, v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}