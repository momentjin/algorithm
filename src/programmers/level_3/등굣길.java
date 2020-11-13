package programmers.level_3;


/**
 * 종류 : #DP
 * 시작일시 : 2020-11-14 12:22
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 등굣길 {

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};

        int x = new 등굣길().solution(m, n, puddles);
        System.out.println(x);
    }

    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];

        for (int[] puddle : puddles)
            map[puddle[1] - 1][puddle[0] - 1] = -1;

        map[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }

                if (i != 0)
                    map[i][j] += map[i - 1][j] % 1000000007;

                if (j != 0)
                    map[i][j] += map[i][j - 1] % 1000000007;
            }
        }

        return map[n - 1][m - 1] % 1000000007;
    }
}
