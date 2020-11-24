package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #그리디 #다시풀기(완)
 * 시작일시 : 2020-11-05 00:14
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 그리디 설계 방법을 잘 모르겠다 ㅠ
 * <p>
 * 이 문제의 핵심은
 * 1. 정렬
 * 2. 처음과 끝을 짝지어 쌍을 만드는 것 (최대한 많이 타려면 MAX와 MIN을 합했을 때 limit이하이어야 하는게 최대한 덜 낭비하므로, 이것이 Greedy.)
 */
public class 구명보트 {

    public int solution(int[] people, int limit) {

        Arrays.sort(people);

        int answer = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {

            int lv = people[left];
            int rv = people[right];

            if (lv + rv > limit) {
                answer++;
                right--;
            } else {
                answer++;
                left++;
                right--;
            }
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        new 구명보트().solution(new int[]{50, 70, 80}, 100);
    }

}
