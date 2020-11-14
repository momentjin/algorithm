package programmers.level_3;


import java.util.Arrays;

/**
 * 종류 : #DP
 * 시작일시 : 2020-11-14 23:47
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 정수삼각형 {

    public int solution(int[][] triangle) {

        int[][] a = new int[triangle.length][triangle.length];
        a[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {

                int s1 = j < a[i - 1].length ? a[i - 1][j] : 0;
                int s2 = j != 0 ? a[i - 1][j - 1] : 0;

                a[i][j] = Integer.max(s1, s2) + triangle[i][j];
            }
        }

        return Arrays.stream(a[triangle.length - 1]).max().getAsInt();
    }
}
