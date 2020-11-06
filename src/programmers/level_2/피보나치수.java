package programmers.level_2;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 종류 : #구현,
 * 시작일시 : 2020-10-30 01:35
 * 성공/실패 : 성공
 * 소요시간 : 금방 풀었음
 */
public class 피보나치수 {

    public int solution(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = new BigInteger("0");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("1");

        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1].add(dp[i-2]);
        }

        return dp[n].mod(new BigInteger("1234567")).intValue();
    }

    public static void main(String[] args) {
        long n = new 피보나치수().solution(10000);
        System.out.println(n);
    }
}
