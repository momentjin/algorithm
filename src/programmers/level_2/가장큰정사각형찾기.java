package programmers.level_2;

/**
 * 종류 : #DP #다시풀기(완)
 * 시작일시 : 2020-10-28 20:49
 * 성공/실패 : 실패
 * 소요시간 :
 *
 * DP로 풀어야하는지 생각도 못했다.
 */
public class 가장큰정사각형찾기 {

    static int memo[][];

    public int solution(int[][] board) {

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1]) + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                max = Math.max(board[i][j], max);
            }
        }

        System.out.println(max);

        return max * max;
    }


    public static void main(String[] args) {

//        int[][] arr = {{0, 0, 1, 1}, {1, 1, 1, 1}};
//        int[][] arr = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        int[][] arr = {{1, 0}, {0, 0}};
        new 가장큰정사각형찾기().solution(arr);
    }
}
