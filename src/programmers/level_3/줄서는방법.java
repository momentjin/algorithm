package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 종류 : #구현
 * 시작일시 :
 * 성공/실패 : 실패
 * 소요시간 :
 *
 * 실패 사유
 * - 알고리즘에 근접했으나, 끝까지 풀지 못함
 * - long, int 제대로 처리 안함
 */
public class 줄서는방법 {

    public static void main(String[] args) {
        int n = 3;
        long k = 4;
        int[] a = new 줄서는방법().solution(n, k);
        System.out.println(Arrays.toString(a));
    }

    public int[] solution(int n, long k) {

        int idx = 0;
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        k--;
        for (int i = 1; i <= n; i++) {

            long factorialN = factorialN(n - i + 1);
            long unit = (factorialN / (n - i + 1));

            int n1 = ((int) (k / unit));
            k = k % unit;
            int v = list.get(n1);
            answer[idx++] = v;
            list.remove(n1);
        }

        return answer;
    }

    public long factorialN(int n) {
        if (n == 1) return 1;
        return n * factorialN(n - 1);
    }
}
