package programmers.level_2;

/**
 * 종류 : #
 * 시작일시 : 2020-11-01 20:58
 * 성공/실패 :
 * 소요시간 :
 */
public class 행렬의곱셈 {

    public static void main(String[] args) {

        int[][] A = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] B = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};

        new 행렬의곱셈().productMatrix(A, B);

    }

    public int[][] solution(int[][] arr1, int[][] arr2) {

        int row1 = arr1.length;
        int col1 = arr1[0].length;

        int row2 = arr2.length;
        int col2 = arr2[0].length;

        int[][] answer = new int[row1][col1];

        for (int x = 0; x < row1; x++) {
            for (int y = 0; y < col1; y++) {


                int a = arr1[x][y];

                for (int y2 = 0; y < col2; y++) {
                    for (int x2 = 0; x < row2; x++) {

                        int b = arr2[x2][y2];


                    }
                }


            }
        }

        return answer;
    }

    public int[][] productMatrix(int[][] A, int[][] B) {
        int r = A.length; // A의 행 갯수
        int aC = A[0].length; // A의 열 갯수
        int bC = B[0].length; // B의 열 갯수
        int[][] answer = new int[r][bC];

        // 시간복잡도 O(N^3)
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < bC; j++) {
                for (int k = 0; k < aC; k++) {
                    System.out.println(String.format("(%d,%d), (%d,%d)", i, k, k, j));
                    answer[i][j] += A[i][k] * B[k][j];
                }
                System.out.println();
            }
        }

        return answer;
    }

}
