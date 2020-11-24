package programmers.level_2;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-01 20:58
 * 성공/실패 : 실패
 * 소요시간 :
 */
public class 행렬의곱셈 {

    public static void main(String[] args) {

        int[][] A = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] B = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};

        new 행렬의곱셈().solution(A, B);

    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = productMatrix(arr1, arr2);
        return answer;
    }

    public int[][] productMatrix(int[][] A, int[][] B) {
        int r = A.length;
        int aC = A[0].length;
        int bC = B[0].length;
        int[][] answer = new int[r][bC];

        // 시간복잡도 O(N^3)
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < bC; j++) {
                for(int k = 0; k < aC; k++) {
                    answer[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return answer;
    }

}
