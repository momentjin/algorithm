package programmers.level_2;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-28 17:50
 * 성공/실패 : 성공
 * 소요시간 : 30
 */
public class 예상대진표 {

    public int solution(int n, int a, int b) {

        int count = 1;
        while (true) {

            if (a < b && a % 2 != 0 && b % 2 == 0 && a + 1 == b)
                break;

            if (b < a && b % 2 != 0 && a % 2 == 0 && b + 1 == a) break;

            a = a % 2 == 0 ? a / 2 : (a + 1) / 2;
            b = b % 2 == 0 ? b / 2 : (b + 1) / 2;

            count++;

        }

        return count;
    }
}
