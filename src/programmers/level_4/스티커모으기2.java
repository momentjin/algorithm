package programmers.level_4;

/**
 * #DP
 * 소요 시간: 10분
 * 도둑질문제랑 똑같음
 */
public class 스티커모으기2 {

    public int solution(int[] money) {

        if (money.length == 1) {
            return money[0];
        }

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

        return Math.max(dp[money.length - 2], dp2[money.length - 1]);
    }
}
