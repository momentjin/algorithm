package programmers.level_2;

/**
 * 종류 : #구현
 * 시작일시 : 2020-12-05 ~~
 * 성공/실패 : 성공
 */
public class 점프와순간이동 {

    public int solution(int n) {

        int a = 0;
        while (n > 0) {

            if (n % 2 != 0) a++;
            n = n / 2;
        }

        return a;
    }
}
