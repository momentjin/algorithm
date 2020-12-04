package programmers.level_3;

/**
 * lv3
 * #DP #다시풀기(완)
 * 실패
 */
public class 보행자천국 {


    final static int MOD = 20170805;

    static int dp[][][];

    public static int solution(int m, int n, int[][] cityMap) {

        dp = new int[500][500][2];

        int M = m;
        int N = n;

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                dp[i][j][0] = dp[i][j][1] = 0;
            }
        }

        dp[0][0][0] = dp[0][0][1] = 1;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {

                // 아래 이동
                // (1) 아래로 이동이 가능하면
                if (i + 1 < M && cityMap[i][j] == 0) {
                    dp[i + 1][j][0] += dp[i][j][0]; // 이전 위치인 위 칸의 값을 더한다
                    if (!(i == 0 && j == 0)) { // 만약 첫 위치가 아니라면
                        dp[i + 1][j][0] += dp[i][j][1]; // 오른쪽 위치도 더한다
                    }
                }
                else if (i + 1 < M && cityMap[i][j] == 2) {
                    dp[i + 1][j][0] += dp[i][j][0];
                }

                // 오른쪽 이동
                if (j + 1 < N && cityMap[i][j] == 0) {
                    dp[i][j + 1][1] += dp[i][j][1];
                    if (!(i == 0 && j == 0)) {
                        dp[i][j + 1][1] += dp[i][j][0];
                    }
                }
                else if (j + 1 < N && cityMap[i][j] == 2) {
                    dp[i][j + 1][1] += dp[i][j][1];
                }

                dp[i + 1][j][0] = dp[i + 1][j][0] % MOD;
                dp[i][j + 1][1] = dp[i][j + 1][1] % MOD;
            }
        }
        return ((dp[M - 1][N - 1][0] + dp[M - 1][N - 1][1])) % MOD;
    }

}
