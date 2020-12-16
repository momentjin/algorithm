package programmers.level_4;

/**
 * #DP
 * 성공
 */
class 도둑질 {

    public static void main(String[] args) {

        int[] m = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = new 도둑질().solution(m);
        System.out.println("n = " + n);
    }

    public int solution(int[] money) {

        int[] dp = new int[money.length];
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);

        for (int i = 2; i < money.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], money[i] + dp[i - 2]);
        }

        int[] dp2 = new int[money.length];
        dp2[0] = 0;
        dp2[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], money[i] + dp2[i - 2]);
        }

        return Math.max(dp[money.length-2], dp2[money.length-1]);
    }
}