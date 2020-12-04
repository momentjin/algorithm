package programmers.level_3;

/**
 * lv3
 * <p>
 * 종류 : #DP, #다시풀기(완)
 * 시작일시 :
 * 성공/실패 : 실패
 */
public class 거스름돈 {

    public static void main(String[] args) {

        int n = 4;
        int[] money = {1, 2};
        int r = new 거스름돈().solution(n, money);
        System.out.println(r);
    }

    public int solution(int n, int[] money) {
        int[] answer = new int[100001];
        answer[0] = 1;
        for(int i : money) {
            for(int j = i; j <= n; j++) {
                answer[j] += answer[j-i];
            }
        }
        return answer[n];
    }

}
