package practice;

import java.util.Arrays;

public class 조합 {

    public static void main(String[] args) {

        func();
        System.out.println();

        func2();
    }

    // 이진법으로 서로 다른 N개의 원소를 선택하는 방법
    public static void func() {
        int n = 16; //
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%04d", Integer.parseInt(Integer.toBinaryString(i))));
        }
    }

    // dp 방식의 조합 (nCr 구하기)
    private static void func2() {

        int ROW = 5;
        long[][] comb = new long[ROW + 1][ROW + 1];
        comb[0][0] = 1;

        for (int i = 1; i <= ROW; i++) {
            for (int j = 0; j <= ROW; j++) {

                if (j == 0) comb[i][j] = 1;
                else if (i == j) comb[j][j] = 1;
                else comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }

        // 5C3 = 10
        System.out.println("comb[5][3] = " + comb[5][3]);
    }
}
