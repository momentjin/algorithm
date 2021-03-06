package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #DFS #다시풀기제(완)
 * 시작일시 : 2020-10-28 18:48
 * 성공/실패 : 성공
 * 소요시간 : 60분 이상.. 초반에 좀 삽질했음.. 그래도 풀긴 풀었드아.
 */
public class 쿼드압축후개수세기 {

    static int cnt0 = 0;
    static int cnt1 = 0;

    public int[] solution(int[][] arr) {


        int size = arr.length;
        func(arr, arr.length, 0, 0, size, size);


        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr.length; j++) {
                if (arr[i][j] == 0) cnt0++;
                else if (arr[i][j] == 1) cnt1++;

            }
        }

        int[] answer = new int[]{cnt0, cnt1};
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public void func(int[][] arr, int size, int x1, int y1, int x2, int y2) {

        if (size == 1)
            return;

        // 검사 & merge
        merge(arr, x1, y1, x2, y2);

        int div = size / 2;
        if (div == 1) return;

        func(arr, size/2, x1, y1, x1 + div, y1 + div);
        func(arr, size/2, x1, y1 + div, x1 + div, y1 + size);
        func(arr, size/2, x1 + div, y1, x1 + size, y1 + div);
        func(arr, size/2, x1 + div, y1 + div, x1 + size, y1 + size);
    }

    public void merge(int[][] arr, int x1, int y1, int x2, int y2) {

        int temp = -1;
        boolean success = true;

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {

                if (arr[i][j] == 2)
                    return;

                if (temp == -1)
                    temp = arr[i][j];
                else {
                    if (temp != arr[i][j]) {
                        success = false;
                    }
                }
            }
        }

        if (success) {
            if (temp == 0) cnt0++;
            else cnt1++;

            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    arr[i][j] = 2;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][] arr = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        int[][] arr = {{1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}};
        new 쿼드압축후개수세기().solution(arr);
    }

    // 재귀를 깔끔하게 쓴 예
    private int[] dfs(int size, int y, int x, int[][] arr) {
        if (size == 1){
            if (arr[y][x] == 0) {
                return new int[]{1, 0};
            }
            if (arr[y][x] == 1) {
                return  new int[]{0, 1};
            }
        }

        int[] result = new int[2];
        int[] leftUp = dfs(size/2, y, x, arr);
        int[] rightUp = dfs(size/2 ,y, x + size/2 , arr);
        int[] leftDown = dfs(size/2, y + size/2 , x , arr);
        int[] rightDown = dfs(size/2, y+ size/2, x + size/2, arr);

        result[0] = leftUp[0] + rightUp[0] + leftDown[0] + rightDown[0];
        result[1] = leftUp[1] + rightUp[1] + leftDown[1] + rightDown[1];

        if (result[0] == 4 && result[1] == 0) result[0] = 1;
        else if (result[0] == 0 && result[1] == 4) result[1] = 1;
        return result;
    }
}
