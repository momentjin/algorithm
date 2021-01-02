package programmers.level_4;

import java.util.Arrays;

/**
 * #DP
 * 성공
 * 소요시간: 1시간 10분 ..
 */
public class 최적의행렬곱셈 {

    public static void main(String[] args) {
        int[][] m = {{20, 1}, {1, 30}, {30, 10}, {10, 10}};
        int n = new 최적의행렬곱셈().solution(m);
        System.out.println(n);
    }

    public int solution(int[][] matrix_sizes) {

        int[][] dp = new int[matrix_sizes.length + 1][matrix_sizes.length + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        int[] numbers = new int[matrix_sizes.length + 1];
        for (int i = 0; i < matrix_sizes.length; i++) {
            numbers[i] = matrix_sizes[i][0];
            if (i == matrix_sizes.length - 1) numbers[i + 1] = matrix_sizes[i][1];
        }

        for (int n = 0; n < matrix_sizes.length; n++) {
            for (int i = 1; i <= matrix_sizes.length - n; i++) {

                int j = i + n;
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }

                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + numbers[i - 1] * numbers[k] * numbers[j]);
                }
            }
        }

        return dp[1][matrix_sizes.length];
    }
}
