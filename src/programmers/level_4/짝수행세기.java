package programmers.level_4;

/**
 * 종류 : #dp
 * 시작일시 : 20.12.12
 * 성공/실패 : 실패
 * 실패 사유
 * - 그냥 어려움.
 */
public class 짝수행세기 {

    public static void main(String[] args) {

//        int[][] a = {
//                {1, 0, 0, 1, 1},
//                {0, 0, 0, 0, 0},
//                {1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 1}
//        };
//        int a[][] = {{0, 1, 0}, {1, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int a[][] = {{1, 0, 0}, {1, 0, 0}};

        int n = new 짝수행세기().solution(a);
        System.out.println(n);
    }

    static int ROW, COL;
    static long[][] dp;
    static long[][] comb;

    public int solution(int[][] a) {

        // init
        ROW = a.length;
        COL = a[0].length;
        dp = new long[COL + 2][ROW + 1];

        // 미리 조합 경우의 수 생성
        initCombinations();

        // i열에 대한 1의 갯수 저장
        int cnt1[] = new int[COL];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                cnt1[j] += a[i][j];
            }
        }

        // dp[0] 초기화
        dp[1][ROW - cnt1[0]] = comb[ROW][ROW - cnt1[0]];

        for (int Column = 1; Column < COL; Column++) {
            int OneCnt = cnt1[Column];
            for (int Even_Num = 0; Even_Num <= ROW; Even_Num++) {
                if (dp[Column][Even_Num] == 0) continue;
                for (int K = 0; K <= OneCnt; K++) {
                    if (Even_Num < K) continue;

                    int Be_Even_Row = Even_Num + OneCnt - (2 * K);
                    if (Be_Even_Row > ROW) continue;

                    long Result = (comb[Even_Num][K] * comb[ROW - Even_Num][OneCnt - K]) % MOD;
                    dp[Column + 1][Be_Even_Row] = (dp[Column + 1][Be_Even_Row] + dp[Column][Even_Num] * Result);
                    dp[Column + 1][Be_Even_Row] %= MOD;
                }
            }
        }

        return (int) dp[COL][ROW];
    }

    private void initCombinations() {

        comb = new long[ROW + 1][ROW + 1];
        comb[0][0] = 1;

        for (int i = 1; i <= ROW; i++) {
            for (int j = 0; j <= ROW; j++) {

                if (j == 0) comb[i][j] = 1;
                else if (i == j) comb[j][j] = 1;
                else comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];

                comb[i][j] %= MOD;
            }
        }
    }

    static long MOD = 10000019;
}
