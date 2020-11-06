package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #그리디
 * 시작일시 : 2020-11-05 00:14
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 그리디 설계 방법을 잘 모르겠다 ㅠ
 */
public class 구명보트 {

    public int solution(int[] people, int limit) {

        int answer = 0;
        Arrays.sort(people);

        for (int l = 0, r = people.length - 1; l <= r; l++) {
            while (l < r && people[l] + people[r--] > limit) {
                answer++;
            }
            answer++;
        }

        System.out.println(answer);

        return answer;
    }

    int solution2(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0;
        for (int i = 0, index = people.length - 1; i <= index; i++, answer++)
            while (index > i && people[i] + people[index--] > limit)
                answer++;

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        new 구명보트().solution(new int[]{50, 70, 80, 90}, 130);
    }

}
