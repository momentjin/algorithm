package programmers.level_2;

import java.util.Stack;

/**
 * 종류 : #스택 #다시풀기(완)
 * 시작일시 : 2020-11-01 22:41
 * 성공/실패 : 실패
 * 소요시간 : 30분
 * 실패 사유
 */
public class 짝찌어제거하기 {

    public static void main(String[] args) {
        String s = "baaabbccddeeffgghhiijjab";
        int result = solution(s);
        System.out.println(result);
    }


    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray())
            if (!stack.isEmpty() && stack.peek() == c) stack.pop();
            else stack.push(c);

        return stack.isEmpty() ? 1 : 0;
    }
}
