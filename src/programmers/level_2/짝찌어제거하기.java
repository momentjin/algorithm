package programmers.level_2;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 종류 : #스택
 * 시작일시 : 2020-11-01 22:41
 * 성공/실패 : 실패
 * 소요시간 : 30분
 * 실패 사유
 * - 간과한 부분을 놓쳤음 ㅜ
 * - 그래도 단순히 문자열 조작이 아닌, 스택을 사용해서 풀어야겠다고 생각했음!
 */
public class 짝찌어제거하기 {

    public static void main(String[] args) {
        String s = "baaabbccddeeffgghhiijjab";
        int n = new 짝찌어제거하기().solution(s);
        int n2 = solution2(s);
        System.out.println(n);
        System.out.println(n2);
    }

    public int solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (stack.contains(c)) {
                char popedC = stack.pop();
                if (c != popedC)
                    return 0;
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }

    public static int solution2(String s) {
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray())
            if(!stack.isEmpty() && stack.peek() == c) stack.pop();
            else stack.push(c);

        return stack.isEmpty() ? 1 : 0;
    }
}
