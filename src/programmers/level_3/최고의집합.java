package programmers.level_3;

import java.util.*;

/**
 * 종류 : #구현
 * 시작일시 :
 * 성공/실패 : 성공
 * 소요시간 : 25분
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
