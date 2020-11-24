package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-30 00:31
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 */
public class 최솟값만들기 {

    public int solution(int[] A, int[] B) {

        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0;
        int j = B.length - 1;
        int answer = 0;

        for (; i < B.length; i++, j--) {
            answer += A[i] * B[j];
        }

        return answer;
    }

    public static void main(String[] args) {
        int a[] = {1, 4, 2};
        int b[] = {5, 4, 4};
        int min = new 최솟값만들기().solution(a, b);
        System.out.println();
        System.out.println(min);
    }
}
