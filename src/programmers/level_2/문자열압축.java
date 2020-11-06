package programmers.level_2;

import java.util.*;

/**
 * 종류 : #단순
 * 성공/실패 : 성공
 */
public class 문자열압축 {

    public static void main(String[] args) {

        int min = new 문자열압축().solution("ababcdcdababcdcd");
        System.out.println(min);
    }

    public int solution(String s) {

        int min = 10000000;
        for (int i = 1; i <= s.length(); i++) {
            min = Integer.min(min, calculate(i, s));
        }

        return min;
    }

    public int calculate(int k, String s) {

        List<String> splitedStrs = new ArrayList<>();
        for (int i = 0; i < s.length(); i = i + k) {
            if (i + k >= s.length()) {
                splitedStrs.add(s.substring(i));
            } else {
                String current = s.substring(i, i + k);
                splitedStrs.add(current);
            }
        }

        int currNum = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splitedStrs.size() - 1; i++) {
            String curr = splitedStrs.get(i);
            String next = splitedStrs.get(i + 1);
            if (curr.equals(next)) {
                currNum++;
            } else {
                if (currNum == 1) sb.append(curr);
                else sb.append(currNum).append(curr);
                currNum = 1;
            }
        }

        if (currNum >= 1) {
            if (currNum == 1) sb.append(splitedStrs.get(splitedStrs.size() - 2));
            else sb.append(currNum).append(splitedStrs.get(splitedStrs.size() - currNum));
        }

        return sb.toString().length();
    }
}
