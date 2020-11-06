package programmers.level_2;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-28 17:46
 * 성공/실패 : 실패
 * 소요시간 : 1시간
 * 실패 사유 : 규칙을 거의 다 찾았는데, 완전하게 찾진 못해서
 */
public class 점프와순간이동 {

    public static void main(String[] args) {

    }

    public int solution(int n) {

        int a = 0;
        while (n > 0) {

            if (n % 2 != 0) a++;
            n = n / 2;
        }

        return a;
    }
}
