package programmers.level_3;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 종류 : #BFS, #DFS
 * 시작일시 : 2020-11-03 01:31
 * 성공/실패 : 실
 * 소요시간 :
 *
 * 실패 사유 :
 * - DFS는 시간 초과 발생 (최단거리는 BFS로 하기)
 * - BFS는 visit 처리를 제대로 하지 못해서 2문제 실패함.
 */
public class 경주로건설 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}};
        int a = new 경주로건설().solution(board);
        System.out.println(a);
    }

    static int size;
    static int[][] visit;
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board) {

        size = board.length;
        visit = new int[size][size];
        answer = Integer.MAX_VALUE;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, -1));
        board[0][0] = 1;

        while (!q.isEmpty()) {

            Point curr = q.poll();

            int cX = curr.x;
            int cY = curr.y;
            int cD = curr.d;
            int cSum = curr.sum;

            if (cX == size - 1 && cY == size - 1) {
                answer = Math.min(answer, cSum);
                System.out.println(answer);
                continue;
            }

            if (cSum >= answer) {
                continue;
            }

            System.out.println(curr);

            for (int mD = 0; mD < 4; mD++) {

                int mX = dx[mD] + cX;
                int mY = dy[mD] + cY;

                int mSum = cSum + 100;

                if (cD == -1) {
                } else if ((cD == 0 || cD == 1) && (mD == 2 || mD == 3)) {
                    mSum += 500;
                } else if ((cD == 2 || cD == 3) && (mD == 0 || mD == 1)) {
                    mSum += 500;
                }

                if (mX < 0 || mY < 0 || mX >= size || mY >= size || board[mX][mY] == 1) {
                    continue;
                }

                if (visit[mX][mY] == 0) {
                    visit[mX][mY] = mSum;
                    q.add(new Point(mX, mY, mSum, mD));
                }
                else {
                    if (visit[mX][mY] >= mSum) {
                        visit[mX][mY] = mSum;
                        q.add(new Point(mX, mY, mSum, mD));
                    }
                }
            }
        }

        return answer;
    }

    class Point {
        int x, y, sum, d;

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ",y=" + y +
                    ",sum=" + sum +
                    ",d=" + d +
                    '}';
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int sum, int d) {
            this.x = x;
            this.y = y;
            this.sum = sum;
            this.d = d;
        }
    }


//    public void dfs(int[][] board, int cX, int cY, int sum, int cD) {
//
//        if (cX == size - 1 && cY == size - 1) {
//            answer = Integer.min(answer, sum);
//            return;
//        }
//
//        if (sum >= answer) {
//            return;
//        }
//
//        for (int mD = 0; mD < 4; mD++) {
//
//            int mX = dx[mD] + cX;
//            int mY = dy[mD] + cY;
//
//            if (mX >= 0 && mY >= 0 && mX < size && mY < size && !visit[mX][mY] && board[mX][mY] == 0) {
//                visit[mX][mY] = true;
//
//                int tempSum = sum + 100;
//
//                if (cD == -1) {
//                } else if ((cD == 0 || cD == 1) && (mD == 2 || mD == 3)) {
//                    tempSum += 500;
//                } else if ((cD == 2 || cD == 3) && (mD == 0 || mD == 1)) {
//                    tempSum += 500;
//                }
//
//                dfs(board, mX, mY, tempSum, mD);
//                visit[mX][mY] = false;
//            }
//        }
//    }
}
