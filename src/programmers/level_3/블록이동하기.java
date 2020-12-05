package programmers.level_3;

import java.util.*;


/**
 * #BFS, #다시풀기(완)
 * 실패
 * 실패 사유
 * - 문제 똑바로 안읽음..
 */
public class 블록이동하기 {

    static int size;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][][] visited;

    public int solution(int[][] board) {

        visited = new boolean[board.length][board.length][2];
        size = board.length;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(
                new Point(0, 0),
                new Point(0, 1),
                0,
                0
        ));

        int answer = 0;

        while (!q.isEmpty()) {

            Pair pair = q.poll();

            if ((!(pair.p1.x >= 0 && pair.p1.y >= 0 && pair.p1.x < size && pair.p1.y < size && map[pair.p1.x][pair.p1.y] == 0) ||
                    !(pair.p2.x >= 0 && pair.p2.y >= 0 && pair.p2.x < size && pair.p2.y < size && map[pair.p2.x][pair.p2.y] == 0))) {
                continue;
            }

            if (board[pair.p1.x][pair.p1.y] == 1 || board[pair.p2.x][pair.p2.y] == 1) {
                continue;
            }

            if (visited[pair.p1.x][pair.p1.y][pair.d] && visited[pair.p2.x][pair.p2.y][pair.d]) {
                continue;
            }

            // 도착지 체크
            if ((pair.p1.x == board.length - 1 && pair.p1.y == board.length - 1) ||
                    (pair.p2.x == board.length - 1 && pair.p2.y == board.length - 1)) {
                answer = pair.cnt;
                break;
            }

            visited[pair.p1.x][pair.p1.y][pair.d] = true;
            visited[pair.p2.x][pair.p2.y][pair.d] = true;

            // 상하좌우 이동 추가
            for (int i = 0; i < 4; i++) {
                q.add(pair.move(i));
            }

            // 회전 이동
            for (int r = 0; r < 2; r++) {
                List<Pair> pairs = pair.d == 0 ? pair.rotateHorizontal(r) :
                        pair.rotateVertical(r);

                q.addAll(pairs);
            }
        }

        return answer;
    }

    static class Pair {
        Point p1, p2;
        int d, cnt; // 가로 0, 세로 1

        public Pair(Point p1, Point p2, int d, int cnt) {
            this.p1 = p1;
            this.p2 = p2;
            this.d = d;
            this.cnt = cnt;
        }

        public Pair move(int direction) {
            return new Pair(p1.move(direction), p2.move(direction), this.d, this.cnt + 1);
        }

        public int changeD() {
            return d == 0 ? 1 : 0;
        }

        public List<Pair> rotateHorizontal(int r) {


            List<Pair> pairs = new ArrayList<>();

            // p1이 축
            if (r == 0) {
                if (p2.x - 1 >= 0 && map[p2.x - 1][p2.y] == 0) {
                    Point moveP1_up = p1.move(0);
                    Pair nP1 = new Pair(p1, moveP1_up, changeD(), this.cnt + 1);
                    pairs.add(nP1);
                }

                if (p2.x + 1 < size && map[p2.x + 1][p2.y] == 0) {
                    Point moveP1_down = p1.move(1);
                    Pair nP2 = new Pair(p1, moveP1_down, changeD(), this.cnt + 1);
                    pairs.add(nP2);

                }
            }

            // p2가 축
            else {
                if (p1.x - 1 >= 0 && map[p1.x - 1][p1.y] == 0) {
                    Point moveP2_up = p2.move(0);
                    Pair nP1 = new Pair(p2, moveP2_up, changeD(), this.cnt + 1);
                    pairs.add(nP1);
                }

                if (p1.x + 1 < size && map[p1.x + 1][p1.y] == 0) {
                    Point moveP2_down = p2.move(1);
                    Pair nP2 = new Pair(p2, moveP2_down, changeD(), this.cnt + 1);
                    pairs.add(nP2);
                }
            }

            return pairs;
        }

        public List<Pair> rotateVertical(int r) {

            List<Pair> pairs = new ArrayList<>();

            // p1이 축
            if (r == 0) {
                if (p2.y - 1 >= 0 && map[p2.x][p2.y - 1] == 0) {
                    Point moveP1_left = p1.move(2);
                    Pair nP1 = new Pair(p1, moveP1_left, changeD(), this.cnt + 1);
                    pairs.add(nP1);
                }

                if (p2.y + 1 < size && map[p2.x][p2.y + 1] == 0) {
                    Point moveP1_right = p1.move(3);
                    Pair nP2 = new Pair(p1, moveP1_right, changeD(), this.cnt + 1);
                    pairs.add(nP2);
                }
            }

            // p2가 축
            else {
                if (p1.y - 1 >= 0 && map[p1.x][p1.y - 1] == 0) {
                    Point moveP2_left = p2.move(2);
                    Pair nP1 = new Pair(p2, moveP2_left, changeD(), this.cnt + 1);
                    pairs.add(nP1);
                }
                if (p1.y + 1 < size && map[p1.x][p1.y + 1] == 0) {
                    Point moveP2_right = p2.move(3);
                    Pair nP2 = new Pair(p2, moveP2_right, changeD(), this.cnt + 1);
                    pairs.add(nP2);
                }

            }

            return pairs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return d == pair.d &&
                    Objects.equals(p1, pair.p1) &&
                    Objects.equals(p2, pair.p2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p1, p2, d);
        }

        public boolean isValid() {
            return this.p1 != null && this.p2 != null;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "p1=" + p1 +
                    ", p2=" + p2 +
                    ", d=" + d +
                    '}';
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point move(int d) {
            int mX = x + dx[d];
            int mY = y + dy[d];

            return new Point(mX, mY);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}