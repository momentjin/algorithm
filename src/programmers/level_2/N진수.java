package programmers.level_2;

/**
 * 종류 : #구현 #진법
 * 시작일시 : 2020-10-27 13:46
 * 성공/실패 : 성공
 * 소요시간 :
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class N진수 {
    public String solution(int n, int t, int m, int p) {

        List<String> list = new ArrayList<>();

        int count = 0;
        while (list.size() <= t * m) {
            String[] s = convertN(count, n).split("");
            list.addAll(Arrays.asList(s));
            count++;
        }

        String answer = "";
        for (int i = p-1; i < list.size(); i += m) {
            answer += list.get(i);
        }

        return answer.substring(0, t);
    }

    public static String convertN(int num, int n) {

        String result = "";

        while (true) {

            int a = num / n;
            int b = num % n;

            if (b >= 10) {
                result = Character.toString((char) ('A' + b - 10)) + result;
            } else {
                result = b + result;
            }
            num = a;

            if (a == 0) break;
        }

        return result;
    }
}