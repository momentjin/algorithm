package programmers.level_3;

/**
 * lv3
 * #특수 (플로이드 와샬 알고리즘), #다시풀기(완)
 * 실패
 */
public class 순위 {

    public static void main(String[] args) {
        int n = 5;
        int[][] arr = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int r = new 순위().solution(n, arr);
        System.out.println(r);
    }

    int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] graph = new boolean[n+1][n+1];

        // 승리한 경우 추가
        for (int[] r : results) {
            int winner = r[0];
            int loser = r[1];
            graph[winner][loser] = true;
        }

        // 플로이드 와샬 적용
        for (int b = 1; b <= n; b++) {
            for (int a = 1; a <= n; a++) {
                for (int c = 1; c <= n; c++) {
                    // a가 b를 이겼고 b가 c를 이겼으면, a가 c를 이겼다고 표시
                    if (graph[a][b] && graph[b][c])
                        graph[a][c] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int winCount = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                // a가 b를 이겼거나 b가 a를 이긴 경우
                if (graph[i][j] || graph[j][i])
                    winCount++;
            }
            // 둘중에 승패가 확실한 경우
            if (winCount == n - 1)
                answer++;
        }

        return answer;
    }
}
