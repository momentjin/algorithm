package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #
 * 시작일시 : 2020-11-02 20:30
 * 성공/실패 : 실패
 * 소요시간 : 구현은 빨리함, 근데 실패함
 * <p>
 * 도대체가 반례를 못찾겠음. 테케 1개있는건 통과하는데.
 * 내가 도대체 놓친게 무엇인걸까.
 */
public class 주식가격 {

    public static void main(String[] args) {

        new 주식가격().solution(new int[]{3, 4, 2, 6, 5});
    }

    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int cnt = 0;
            int base = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (base <= prices[j]) {
                    base = prices[j];
                    cnt++;
                }
            }

            answer[i] = cnt;
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }
}
