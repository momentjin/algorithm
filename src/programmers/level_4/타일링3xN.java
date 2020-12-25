package programmers.level_4;

/**
 * #구현
 * 성공
 * 소요시간: 42분
 */
class 타일링3xN {

    public static void main(String[] args) {
        int n = new 타일링3xN().solution(5000);
        System.out.println("n = " + n);
    }

    public int solution(int n) {

        long[] dp = new long[n + 1];
        dp[1] = 0;
        dp[2] = 3;
        dp[3] = 0;
        dp[4] = 11;


        for (int i = 6; i <= n; i += 2) {

            long sum = dp[i - 2] * 3 + 2;
            for (int j = i - 4; j >= 2; j--) {
                sum += dp[j] * 2;
                sum %= 1000000007;
            }

            dp[i] = sum % 1000000007;
        }

        return (int) dp[n];
    }
}