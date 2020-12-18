package programmers.level_4;

import java.util.*;

/**
 * #DP
 * 실패
 * 실패 사유
 * - 무조건 순차적으로만 생각했던게 잘못이었음.
 * - 구간으로 생각했어야 함.
 */
public class 사칙연산 {

    public static void main(String[] args) {
        String[] s = {"5", "-", "3", "+", "2", "-", "4", "+", "1"};
        int n = new 사칙연산().solution(s);
        System.out.println(n);
    }


    public int solution(String[] arr) {

        int num = arr.length / 2 + 1;
        int[][] dp_max = new int[num][num];
        int[][] dp_min = new int[num][num];

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                dp_max[i][j] = Integer.MIN_VALUE;
                dp_min[i][j] = Integer.MAX_VALUE;
            }
            dp_max[i][i] = Integer.parseInt(arr[i * 2]);
            dp_min[i][i] = Integer.parseInt(arr[i * 2]);
        }

        for (int calc = 1; calc < num; calc++) {
            for (int i = 0; i < num - calc; i++) {
                int j = calc + i;
                for (int k = i; k < j; k++) {
                    if (arr[k * 2 + 1].equals("-")) {
                        dp_max[i][j] = Math.max(dp_max[i][k] - dp_min[k + 1][j], dp_max[i][j]);
                        dp_min[i][j] = Math.min(dp_min[i][k] - dp_max[k + 1][j], dp_min[i][j]);
                    } else if (arr[k * 2 + 1].equals("+")) {
                        dp_max[i][j] = Math.max(dp_max[i][k] + dp_max[k + 1][j], dp_max[i][j]);
                        dp_min[i][j] = Math.min(dp_min[i][k] + dp_min[k + 1][j], dp_min[i][j]);
                    }
                }
            }
        }

        int answer = dp_max[0][num - 1];
        return answer;
    }

    void print(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}
