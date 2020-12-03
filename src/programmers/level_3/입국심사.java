package programmers.level_3;

import java.util.Arrays;

/**
 * lv3
 *
 * 종류 : #이분탐색 #다시풀기(완)
 * 시작일시 : 2020-11-15 12:54
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 이분탐색인건 알겠다.
 * - 근데 N명이 통과할 수 있음을 테스트하는 로직을 작성하지 못했다.
 * -
 */
public class 입국심사 {

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        return binarySearch(times, n, times[times.length - 1]);
    }

    long binarySearch(int[] times, int n, long max) {
        long left = 1, right = max * n;
        long mid = 0;
        long ans = Long.MAX_VALUE;

        while (left <= right) {
            mid = (left + right) / 2;

            if (isPassed(times, n, mid)) {
                ans = ans > mid ? mid : ans;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    boolean isPassed(int[] times, int n, long mid) {
        long amount = 0;
        for (int time : times) {
            amount += mid / time;
        }

        return amount >= n;
    }

    public static void main(String[] args) {
        int n = 6;
        int a[] = {7, 10};
        long r = new 입국심사().solution(n, a);
        System.out.println(r);
    }
}
