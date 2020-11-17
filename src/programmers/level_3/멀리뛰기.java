package programmers.level_3;

/**
 * 종류 : #DP
 * 시작일시 :
 * 성공/실패 : 성공
 * 소요 시간 ; 10분
 */
public class 멀리뛰기 {

    public long solution(int n) {
        int a[] = new int[n+1];
        a[1] = 1;

        if (n>1)
            a[2] = 2;

        for (int i=3; i<=n; i++) {
            a[i] = (a[i-1] + a[i-2]) % 1234567;
        }

        long answer = a[n];
        return answer;
    }

}
