package programmers.level_4;

/**
 * #구현
 * 성공
 * 소요시간 : 40분
 */
public class 숫자블록 {

    public int[] solution(long begin, long end) {

        int size = (int) (end - begin + 1);
        int[] answer = new int[size];
        if (begin == 1) answer[0] = 0;

        for (int i = 0; i < size; i++) {
            int cur = (int) begin + i;
            answer[i] = getNum(cur);
        }

        return answer;
    }

    private int getNum(int cur) {

        // 소수 체크 & 값 구하기
        for (int i = 2; i <= Math.sqrt(cur); i++) {

            if (cur % i == 0) {
                return cur / i;
            }

            if (cur / i < 10000000) {
                return cur / i;
            }
        }

        return 1;
    }
}
