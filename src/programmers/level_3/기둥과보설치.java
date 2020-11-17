package programmers.level_3;


import java.util.*;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-17 12:40
 * 성공/실패 : 실패
 * 소요시간 :
 *
 * 실패 사유
 * - 보, 기둥을 지을 수 없는 조건 = !보, 기둥을 지을 수 있는 조건....인데 부정 조건을 일일히 따져가며 코딩하다보니 버그 생긴 듯..
 */

public class 기둥과보설치 {

    static int BO = 1;
    static int GI = 0;

    static int INSERT = 1;
    static int DELETE = 0;

    public static void main(String[] args) {
        int n = 5;
        int[][] a =
//                {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
                {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};
        int[][] b = new 기둥과보설치().solution(n, a);
        for (int i = 0; i < b.length; i++) {
            System.out.println(Arrays.toString(b[i]));
        }
    }

    public int[][] solution(int n, int[][] build_frame) {

        Point[][] map = new Point[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = new Point();
            }
        }

        for (int[] frame : build_frame) {

            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int op = frame[3];

            if (type == BO) {
                if (op == INSERT && isBo(x, y, map, n)) {
                    map[x][y].set(x, y, BO);
                } else if (op == DELETE && check(map, x, y, type, n)) {
                    map[x][y].removeType(BO);
                }
            } else if (type == GI) {
                if (op == INSERT && isGi(x, y, map)) {
                    map[x][y].set(x, y, GI);
                } else if (op == DELETE && check(map, x, y, type, n)) {
                    map[x][y].removeType(GI);
                }
            }
        }

        List<Point> points = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (!map[i][j].isNull()) {
                    for (int t : map[i][j].types) {
                        points.add(new Point(map[i][j].x, map[i][j].y, t));
                    }
                }
            }
        }

        Collections.sort(points);

        int[][] answer = new int[points.size()][3];
        for (int i = 0; i < points.size(); i++) {
            answer[i][0] = points.get(i).x;
            answer[i][1] = points.get(i).y;
            answer[i][2] = points.get(i).t;
        }
        return answer;
    }

    private boolean check(Point[][] map, int x, int y, int type, int n) {

        map[x][y].removeType(type);

        boolean flag = true;

        loop:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (!map[i][j].isNull())
                    for (int t : map[i][j].types) {
                        if (t == BO && !isBo(i, j, map, n)) {
                            flag = false;
                            break loop;
                        } else if (t == GI && !isGi(i, j, map)) {
                            flag = false;
                            break loop;
                        }
                    }
            }
        }

        map[x][y].set(x, y, type);
        return flag;
    }

    boolean isBo(int x, int y, Point[][] map, int n) {
        return (y - 1 >= 0 && map[x][y - 1].isGI())  // 보 왼쪽에 기둥이 있는 경우
                || (x + 1 <= n && y - 1 >= 0 && map[x + 1][y - 1].isGI())  // 보 오른쪽에 기둥이 있는 경우
                || (x - 1 >= 0 && x + 1 <= n && map[x - 1][y].isBO() && map[x + 1][y].isBO());
    }

    boolean isGi(int x, int y, Point[][] map) {
        return y == 0 // 바닥인 경우
                || (y - 1 >= 0 && map[x][y - 1].isGI()) // 아래에 기둥이 있는 경우
                || (x - 1 >= 0 && map[x - 1][y].isBO()) // 보의 오른쪽 끝에 설치
                || (map[x][y].isBO());
    }

    class Point implements Comparable<Point> {

        int x, y;
        int t;
        List<Integer> types = new ArrayList<>();

        public Point() {
            types = new ArrayList<>();
        }

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        boolean isGI() {
            return types.contains(GI);
        }

        boolean isBO() {
            return types.contains(BO);
        }

        boolean isNull() {
            return types == null || types.isEmpty();
        }

        void set(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.types.add(type);
        }

        void removeType(Integer type) {
            this.types.remove(type);
        }

        @Override
        public int compareTo(Point o) {

            if (this.x == o.x && this.y == o.y) {
                return Integer.compare(this.t, o.t);
            } else if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            } else
                return Integer.compare(this.x, o.x);
        }
    }
}
