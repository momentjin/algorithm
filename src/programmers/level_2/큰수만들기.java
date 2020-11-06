package programmers.level_2;

/**
 * 종류 : #그리디
 * 시작일시 : 2020-11-04 21:30
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 그리디를 잘 못하겠음.
 */
public class 큰수만들기 {

    public static void main(String[] args) {

        new 큰수만들기().solution("194258", 2);
    }

    public String solution(String number, int k) {

        StringBuilder answer = new StringBuilder();
        int size = number.length();
        int start = 0;

        for (int i = 0; i < size - k; i++) {

            int max = number.charAt(start) - '0';
            int maxIdx = start;
            for (int j = start; j <= i + k; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    maxIdx = j;
                }
            }

            answer.append(max);
            start = maxIdx + 1;
        }

        return answer.toString();
    }

}
