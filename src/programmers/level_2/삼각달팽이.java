package programmers.level_2;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-03 01:51
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 풀이를 봤더니 예상 보다 쉬웠다. 이런거 조차 구현을 못하면 안된다!!
 */
public class 삼각달팽이 {

    public static void main(String[] args) {
        new 삼각달팽이().solution(5);
    }

    public int[] solution(int n) {

        final int max = max(n);

        int[][] arr = new int[n][n];
        int[] answer = new int[max];

        int val = 1;
        int x = 0, y = 0;
        arr[x][y] = val;

        while (val < max) {

            while (x + 1 < n && arr[x + 1][y] == 0 && val + 1 <= max) {
                arr[++x][y] = ++val;
            }

            while (y + 1 < n && arr[x][y + 1] == 0 && val + 1 <= max) {
                arr[x][++y] = ++val;
            }

            while (x - 1 >= 0 && y - 1 >= 0 && arr[x - 1][y - 1] == 0 && val + 1 <= max) {
                arr[--x][--y] = ++val;
            }
        }

        int idx = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (arr[j][k] != 0) {
                    answer[idx++] = arr[j][k];
                }
            }
        }

        return answer;
    }

    public int max(int n) {
        return IntStream.rangeClosed(1, n).sum();
    }
}
