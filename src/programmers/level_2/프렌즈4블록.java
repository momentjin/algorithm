package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-27 15:29
 * 종료일시 : 2020-10-27 16:13
 * 성공/실패 : 성공
 * 소요시간 : 43분
 * 회고
 * - 도형을 제거하고, 다시 채울 때 조금 헷갈렸다.
 * - 도형을 제거할 때 임시 배열을 이용했는데, 임시배열을 초기화해주지 않아서 오류가 있었다.
 *
 * <p>
 * Q. 다른 사람들은 도형을 어떻게 움직였을까..? (빈공간 메꾸기)
 * A. 다른 사람들은 3중 반복문을 썼다. 나의 경우에는 재귀였음
 * > 크게 중요해보이진 않을 듯 하다.
 */
public class 프렌즈4블록 {

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        int answer = new 프렌즈4블록().solution(m, n, board);
        System.out.println(answer);


    }

    public int solution(int m, int n, String[] board) {

        String[][] map = new String[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].split("");
        }

        int count = 1;
        while (true) {

//            System.out.println();
//            System.out.println(count + "회차");
            count++;
            boolean[][] temp = new boolean[m][n];

            boolean isChanged = false;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {

                    String curr = map[i][j];
                    if (curr.equals("#"))
                        continue;

                    String right = map[i][j + 1];
                    String down = map[i + 1][j];
                    String rightDown = map[i + 1][j + 1];

                    if (curr.equals(right) && right.equals(down) && down.equals(rightDown)) {
                        temp[i][j] = true;
                        temp[i + 1][j] = true;
                        temp[i][j + 1] = true;
                        temp[i + 1][j + 1] = true;
                        isChanged = true;
                    }
                }
            }

            if (!isChanged)
                break;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (temp[i][j]) {
                        map[i][j] = "#";
                    }
                }
            }
//            print(map);

            while (true) {

                boolean checked = false;

                for (int i = 0; i < m - 1; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!map[i][j].equals("#") && map[i + 1][j].equals("#")) {
                            map[i + 1][j] = map[i][j];
                            map[i][j] = "#";
                            checked = true;
                        }
                    }
                }

                if (!checked)
                    break;
            }

//            print(map);
        }

        int answer = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("#")) answer++;
            }
        }

//        print(map);

        return answer;
    }

    public void print(String[][] res) {

        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        System.out.println();

    }

    public void print(boolean[][] res) {

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] ? 1 : 0 );
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

    }
}
