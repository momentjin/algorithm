package programmers.level_2;

import static practice.조합.func;

/**
 * 종류 : #DFS
 * 시작일시 : 2020-10-28 17:53
 * 성공/실패 : 성
 * 소요시간 : 30분
 */
public class 타겟넘버 {


    static int cnt = 0;

    public int solution(int[] numbers, int target) {

        for (int i = 0; i < 2; i++) {
            int sum = numbers[0];
            if (i == 0) sum = sum * -1;
            func(numbers, target, 0, sum);
        }


        int answer = cnt;
        System.out.println(cnt);
        return answer;
    }

    private void func(int[] numbers, int target, int curIdx, int curSum) {

        if (curIdx == numbers.length - 1) {
            if (curSum == target) cnt++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            curSum = i == 0 ? curSum - numbers[curIdx+1] : curSum + numbers[curIdx+1];

            func(numbers, target, curIdx+1, curSum);

            curSum = i == 0 ? curSum + numbers[curIdx+1] : curSum - numbers[curIdx+1];
        }
    }

    public static void main(String[] args) {
        new 타겟넘버().solution(new int[]{1, 1, 1, 1, 1}, 3);
    }
}
