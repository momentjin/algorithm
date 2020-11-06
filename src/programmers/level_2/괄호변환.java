package programmers.level_2;

import java.util.Stack;

/**
 * 종류 : #재귀 #구현
 * 시작일시 : 2020-11-02 23:26
 * 성공/실패 : 성공
 * 소요시간 : 30
 */
public class 괄호변환 {

    public String solution(String p) {
        String[] answers = recursion(p);
        return answers[0] + answers[1];
    }

    public String[] recursion(String p) {
        String[] s = divide(p);
        String u = s[0];
        String v = s[1];

        if (u.isEmpty() && v.isEmpty()) {
            return s;
        }

        if (isCorrect(u)) {
            u += recursion(v)[0];
        } else {
            StringBuilder empty = new StringBuilder();
            empty.append("(");
            String[] vResult = recursion(v);
            empty.append(vResult[0]).append(vResult[1]);
            empty.append(")");

            StringBuilder reU = new StringBuilder(u);
            reU.deleteCharAt(reU.length() - 1);
            reU.deleteCharAt(0);

            for (int i = 0; i < reU.length(); i++) {
                if (reU.charAt(i) == ')') {
                    reU.setCharAt(i, '(');
                } else {
                    reU.setCharAt(i, ')');
                }
            }

            empty.append(reU);
            return new String[]{empty.toString(), ""};
        }

        return new String[]{u, ""};
    }

    public String[] divide(String s) {

        if (s.isEmpty())
            return new String[]{"", ""};

        int count1 = 0;
        int count2 = 0;
        int idx = 0;

        for (char c : s.toCharArray()) {

            if (count1 != 0 && count1 == count2) {
                break;
            }

            if (c == '(') count1++;
            else count2++;

            idx++;
        }

        return new String[]{s.substring(0, idx), s.substring(idx)};
    }

    public boolean isCorrect(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c);
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                if (')' != c) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String r = new 괄호변환().solution("(())))((");
        System.out.println(r);
    }
}
