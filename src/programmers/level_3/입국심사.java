package programmers.level_3;

import java.util.Arrays;

/**
 * 종류 : #이분탐
 * 시작일시 : 2020-11-15 12:54
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 이분탐색 유형 처음 접해봄.
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
