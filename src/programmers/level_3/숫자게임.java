package programmers.level_3;

import java.util.Arrays;

/**
 * 종류 : #구현, #다시풀기(완)
 * 시작일시 : 2020-11-16 19:00
 * 성공/실패 : 실패
 * 소요시간 :
 * <p>
 * 고정된 리스트 A에 대해 주어진 리스트 B의 원소의 순서를 조작해서 얻을 수 있는 최대값을 얻는 것.
 * => A에 따라 B의 순서가 정해진다.
 * => A가 변화하던 안하던 상관이 없다.
 * => 따라서, A를 정렬시켜도 문제가 없다.
 */
public class 숫자게임 {

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int start = 0;
        for (int a : A) {
            for (int j = start; j < B.length; j++) {
                if (B[j] > a) {
                    answer++;
                    start = j + 1;
                    break;
                }
            }
        }

        return answer;
    }
}
