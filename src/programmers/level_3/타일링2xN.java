package programmers.level_3;

import java.util.Set;

/**
 * 종류 : #DP #다시풀기
 * 시작일시 : 2020-11-06 19:09
 * 성공/실패 : 실패
 * 소요시간 : 30
 * 실패사유 : 완전탐색 불가.
 */
public class 타일링2xN {
    static long answer;
    static char visit[][];
    static Set<String> set;

    public static void main(String[] args) {
        int n = new 타일링2xN().solution(4);
        System.out.println(n);
    }


    public int solution(int n) {
        int a[] = new int[n + 1];
        a[1] = 1;
        a[2] = 2;
        for (int i = 3; i <= n; i++) {
            a[i] = a[i-1] + a[i-2];
            a[i] %= 1000000007;
        }

        return a[n];
    }

}
