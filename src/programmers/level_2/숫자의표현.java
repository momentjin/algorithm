package programmers.level_2;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-29 23:44
 * 성공/실패 :
 * 소요시간 :
 */
public class 숫자의표현 {

    public static void main(String[] args) {
        int n = new 숫자의표현().solution(15);
        System.out.println(n);
    }

    public int solution(int n) {

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = i; j <= n; j++) {
                sum += i;

                if (sum == n) {
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
