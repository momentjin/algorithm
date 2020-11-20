package programmers.level_3;

import java.util.*;

/**
 * 종류 : #구현
 * 시작일시 :
 * 성공/실패 : 실패
 * 소요시간 :
 *
 * 실패 사유
 * - ?
 *
 * 이 문제의 핵심은, 자연수 n개로 가장 큰 집합을 만드려면 n개의 자연수 간 표준편차가 가장 적어야 한다는 것.
 */
public class 최고의집합 {

    public static void main(String[] args) {
        new 최고의집합().solution(3, 14);
    }

    public int[] solution(int n, int s) {

        int h = s / n;
        int d = s % n;

        if (h == 0) return new int[]{-1};

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = h;
        }


        for (int i = 0; i < d; i++) {
            answer[i]++;
        }

        Arrays.sort(answer);

        return answer;
    }
}
