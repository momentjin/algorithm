package programmers.level_2;

/**
 * 종류 : #
 * 시작일시 : 2020-11-01 18:39
 * 성공/실패 :
 * 소요시간 :
 */
public class N개의최대공배수 {

     int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    int solution(int[] arr) {
        int answer = 0;
        int lcm1 = arr[0];
        for (int i = 0; i < arr.length; i++) {

            lcm1 = lcm(lcm1, arr[i]);

        }
        return lcm1;
    }

    int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
