package programmers.level_2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-27 13:46
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 무조건 이진법인 줄알고, 개썅노가다를 했는데, 그럴 필요가 없었네
 */
class 폰켓몬 {

    public int solution(int[] nums) {


        Arrays.sort(nums);

        int half = nums.length / 2;
        int before = nums[0];
        int answer = 1;


        for (int i = 1; i < nums.length; i++) {
            if (answer == half) break;
            if (before != nums[i]) {
                answer++;
                before = nums[i];
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        int n = new 폰켓몬().solution(new int[]{3, 3, 3, 2, 2, 2});
        System.out.println(n);
    }
}